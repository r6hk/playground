package org.example.virtualthread;

/**
 * @author rennen.dev
 * @date 2025/10/11 10:29
 */
public class PlatformThreadVsVirtualThread {

    public static void main(String[] args) throws InterruptedException {

        Thread.Builder builder = Thread.ofVirtual().name("worker-", 0);
        Runnable task = () -> {
            System.out.println("Thread ID: " + Thread.currentThread());
        };

// name "worker-0"
        Thread t1 = builder.start(task);
        t1.join();
        System.out.println(t1.getName() + " terminated");

// name "worker-1"
        Thread t2 = builder.start(task);
        t2.join();
        System.out.println(t2.getName() + " terminated");
    }
}
