package dev.rennen.juc.basic;

/**
 * @author rennen.dev
 * @date 2024/12/8 10:55
 */
public class ThreadStartTest {

    public static void main(String[] args) {
        Thread t1 = new Thread(() -> {
            System.out.println("Thread 1");
        });
        t1.start();
        System.out.println("Main");
    }
}
