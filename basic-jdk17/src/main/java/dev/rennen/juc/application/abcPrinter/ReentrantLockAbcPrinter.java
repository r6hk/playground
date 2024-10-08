package dev.rennen.juc.application.abcPrinter;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author rennen.dev
 * @date 2024/8/7 19:31
 */
public class ReentrantLockAbcPrinter extends ReentrantLock {

    private final int loopNumber;

    public ReentrantLockAbcPrinter (int loopNumber) {
        this.loopNumber = loopNumber;
    }
    //            参数1 打印内容， 参数2 进入哪一间休息室, 参数3 下一间休息室
    public void print(String str, Condition current, Condition next) {
        for (int i = 0; i < loopNumber; i++) {
            lock();
            try {
                current.await();
                System.out.print(str);
                next.signal();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                unlock();
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        ReentrantLockAbcPrinter awaitSignal = new ReentrantLockAbcPrinter(5);
        Condition a = awaitSignal.newCondition();
        Condition b = awaitSignal.newCondition();
        Condition c = awaitSignal.newCondition();
        new Thread(() -> {
            awaitSignal.print("a", a, b);
        }).start();
        new Thread(() -> {
            awaitSignal.print("b", b, c);
        }).start();
        new Thread(() -> {
            awaitSignal.print("c", c, a);
        }).start();

        Thread.sleep(1000);
        awaitSignal.lock();
        try {
            System.out.println("开始...");
            // 通常调用该方法时必须持有和 Condition 相关联的锁
            a.signal();
        } finally {
            awaitSignal.unlock();
        }

    }
}
