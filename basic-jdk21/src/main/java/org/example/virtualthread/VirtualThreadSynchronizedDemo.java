package org.example.virtualthread;

import java.util.concurrent.locks.ReentrantLock;
import java.time.Duration;

public class VirtualThreadSynchronizedDemo {
    private static int counter = 0;
    private static final Object syncLock = new Object();
    private static final ReentrantLock reentrantLock = new ReentrantLock();

    public static void main(String[] args) throws InterruptedException {
        System.out.println("=== 使用 synchronized 的虚拟线程 (不推荐) ===");
        testWithSynchronized();
        
        System.out.println("\n=== 使用 ReentrantLock 的虚拟线程 (推荐) ===");
        testWithReentrantLock();
    }

    // 不推荐：使用 synchronized 会固定（pin）载体线程
    private static void testWithSynchronized() throws InterruptedException {
        counter = 0;
        long start = System.currentTimeMillis();
        
        try (var executor = java.util.concurrent.Executors.newVirtualThreadPerTaskExecutor()) {
            for (int i = 0; i < 1000; i++) {
                executor.submit(() -> {
                    synchronized (syncLock) {
                        counter++;
                        try {
                            Thread.sleep(10);
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    }
                });
            }
        }
        
        long duration = System.currentTimeMillis() - start;
        System.out.println("计数器值: " + counter);
        System.out.println("耗时: " + duration + " ms");
        System.out.println("问题：synchronized 会阻塞载体线程，降低并发性能");
    }

    // 推荐：使用 ReentrantLock 允许虚拟线程正确调度
    private static void testWithReentrantLock() throws InterruptedException {
        counter = 0;
        long start = System.currentTimeMillis();
        
        try (var executor = java.util.concurrent.Executors.newVirtualThreadPerTaskExecutor()) {
            for (int i = 0; i < 1000; i++) {
                executor.submit(() -> {
                    reentrantLock.lock();
                    try {
                        counter++;
                        try {
                            Thread.sleep(10);
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    } finally {
                        reentrantLock.unlock();
                    }
                });
            }
        }
        
        long duration = System.currentTimeMillis() - start;
        System.out.println("计数器值: " + counter);
        System.out.println("耗时: " + duration + " ms");
        System.out.println("优势：ReentrantLock 不会固定载体线程，虚拟线程可以正确挂起和恢复");
    }
}