package org.example.virtualthread;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import java.time.Duration;
import java.time.Instant;
import java.util.concurrent.atomic.LongAdder;

public class DemoVirtualThreadsSync {
    // 可调参数
    static final int THREADS = 20_000;    // 启动的线程数量（对 virtual threads 可以很大）
    static final int ITERATIONS = 100;    // 每个线程的循环次数
    static final boolean INSIDE_SLEEP = false; // 在同步块中是否 sleep（用于放大竞争）

    // 三种计数器实现
    static class SynchronizedCounter {
        private long value = 0L;
        public synchronized void inc() {
            if (INSIDE_SLEEP) {
                try { Thread.sleep(1); } catch (InterruptedException ignored) {}
            }
            value++;
        }
        public long get() { return value; }
    }

    static class AtomicCounter {
        private final AtomicLong a = new AtomicLong();
        public void inc() { a.incrementAndGet(); }
        public long get() { return a.get(); }
    }

    static class LongAdderCounter {
        private final LongAdder ad = new LongAdder();
        public void inc() { ad.increment(); }
        public long get() { return ad.sum(); }
    }

    public static void main(String[] args) throws Exception {
        System.out.println("Demo: virtual threads vs platform threads, comparing synchronized/Atomic/LongAdder");
        System.out.printf("Threads=%d, Iterations=%d, INSIDE_SLEEP=%b%n", THREADS, ITERATIONS, INSIDE_SLEEP);
        System.out.println();

        // 运行 4 场景：
        // 1) virtual + synchronized
        // 2) virtual + AtomicLong
        // 3) virtual + LongAdder
        // 4) platform + synchronized (作对比)
        runScenario("virtual + synchronized", true, SynchronizedCounter::new,
                (c) -> { for (int i=0;i<ITERATIONS;i++) ((SynchronizedCounter)c).inc(); });

        runScenario("virtual + AtomicLong", true, AtomicCounter::new,
                (c) -> { for (int i=0;i<ITERATIONS;i++) ((AtomicCounter)c).inc(); });

        runScenario("virtual + LongAdder", true, LongAdderCounter::new,
                (c) -> { for (int i=0;i<ITERATIONS;i++) ((LongAdderCounter)c).inc(); });

        runScenario("platform + synchronized", false, SynchronizedCounter::new,
                (c) -> { for (int i=0;i<ITERATIONS;i++) ((SynchronizedCounter)c).inc(); });
    }

    // 一个简单的 runner：创建 THREADS 个线程，执行任务，测时
    static <C> void runScenario(String name, boolean useVirtual,
                                java.util.function.Supplier<C> counterSupplier,
                                java.util.function.Consumer<C> taskPerThread) throws Exception {
        System.out.println("=== " + name + " ===");
        C counter = counterSupplier.get();
        List<Thread> threads = new ArrayList<>(THREADS);

        Instant start = Instant.now();
        for (int t=0; t<THREADS; t++) {
            Thread thr = useVirtual
                    ? Thread.ofVirtual().unstarted(() -> taskPerThread.accept(counter))
                    : new Thread(() -> taskPerThread.accept(counter));
            threads.add(thr);
            thr.start();
        }
        // join
        for (Thread th : threads) th.join();
        Instant end = Instant.now();
        Duration d = Duration.between(start, end);

        System.out.printf("Elapsed: %d ms, final count = %d (expected %d)%n",
                d.toMillis(), getCountValue(counter), (long)THREADS * ITERATIONS);
        System.out.println();
    }

    // 反射式获取 count（因为泛型）
    static long getCountValue(Object counter) {
        try {
            if (counter instanceof SynchronizedCounter) return ((SynchronizedCounter) counter).get();
            if (counter instanceof AtomicCounter) return ((AtomicCounter) counter).get();
            if (counter instanceof LongAdderCounter) return ((LongAdderCounter) counter).get();
            return -1L;
        } catch (Exception e) {
            return -1L;
        }
    }
}
