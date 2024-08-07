package dev.rennen.juc.application.abcPrinter;

import java.util.concurrent.Semaphore;

/**
 * @author rennen.dev
 * @date 2024/8/7 19:06
 */
public class SemaphoreAbcPrinter {

    private final int max;
    // 从线程 A 开始执行
    private final Semaphore semaphoreA = new Semaphore(1);
    private final Semaphore semaphoreB = new Semaphore(0);
    private final Semaphore semaphoreC = new Semaphore(0);

    public SemaphoreAbcPrinter(int max) {
        this.max = max;
    }

    public void printA() {
        print("A", semaphoreA, semaphoreB);
    }

    public void printB() {
        print("B", semaphoreB, semaphoreC);
    }

    public void printC() {
        print("C", semaphoreC, semaphoreA);
    }

    private void print(String alphabet, Semaphore currentSemaphore, Semaphore nextSemaphore) {
        for (int i = 1; i <= max; i++) {
            try {
                currentSemaphore.acquire();
                System.out.println(Thread.currentThread().getName() + " : " + alphabet + "，第" + i + "次");
                // 传递信号给下一个线程
                nextSemaphore.release();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                return;
            }
        }
    }

    public static void main(String[] args) {
        SemaphoreAbcPrinter printer = new SemaphoreAbcPrinter(10);
        Thread t1 = new Thread(printer::printA, "Thread A");
        Thread t2 = new Thread(printer::printB, "Thread B");
        Thread t3 = new Thread(printer::printC, "Thread C");

        t1.start();
        t2.start();
        t3.start();
    }
    
}
