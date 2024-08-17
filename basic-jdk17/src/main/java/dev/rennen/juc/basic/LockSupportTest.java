package dev.rennen.juc.basic;

import java.util.concurrent.locks.LockSupport;

/**
 * @author rennen.dev
 * @date 2024/8/7 20:05
 */
public class LockSupportTest {

    public static void main(String[] args) {
        Thread t1 = new Thread(() -> {
            // 确保来自 Main Thread 的 unpark() 先执行
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Thread 1 - Trying to park");
            // 由于提前被 unpark() 了，所以此次 park() 无效
            LockSupport.park();
            System.out.println("Thread 1 - Resumed after park");
            // 而第二次 park() 能够正常生效，所以 "parked" 不会输出
            LockSupport.park();
            System.out.println("Thread 1 - parked");


        });

        t1.start();
        System.out.println("Main - Unparking Thread 1");
        // 提前 unpark() thread 1，且多次 unpark() 等效一次
        LockSupport.unpark(t1);
        LockSupport.unpark(t1);
        LockSupport.unpark(t1);

    }
}