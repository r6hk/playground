package dev.rennen.template;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * <br/>
 * 2025/3/4 21:34
 *
 * @author rennen.dev
 */
public class PrintAbc {
    ReentrantLock lock = new ReentrantLock();
    Condition conA = lock.newCondition();
    Condition conB = lock.newCondition();
    Condition conC = lock.newCondition();

    Thread ta = new Thread(() -> {
        lock.lock();
        try {
            conA.await();

        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    });
}
