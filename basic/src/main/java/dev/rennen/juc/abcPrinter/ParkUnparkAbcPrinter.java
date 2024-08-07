package dev.rennen.juc.abcPrinter;

import java.util.concurrent.locks.LockSupport;

/**
 * @author rennen.dev
 * @date 2024/8/7 19:30
 */
public class ParkUnparkAbcPrinter {

    static Thread t1, t2, t3;

    public void print(String str, Thread next) {
        for (int i = 0; i < loopNumber; i++) {
            LockSupport.park();
            System.out.print(str);
            LockSupport.unpark(next);
        }
    }

    private int loopNumber;

    public ParkUnparkAbcPrinter(int loopNumber) {
        this.loopNumber = loopNumber;
    }

    public static void main(String[] args) {
        ParkUnparkAbcPrinter pu = new ParkUnparkAbcPrinter(5);
        t1 = new Thread(() -> {
            pu.print("a", t2);
        });
        t2 = new Thread(() -> {
            pu.print("b", t3);
        });
        t3 = new Thread(() -> {
            pu.print("c", t1);
        });
        t1.start();
        t2.start();
        t3.start();
        LockSupport.unpark(t1);
    }
}
