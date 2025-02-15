package dev.rennen;

import java.util.concurrent.locks.ReentrantLock;

/**
 * @author rennen.dev
 * @date 2024/8/9 19:54
 */
//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        ReentrantLock lock = new ReentrantLock();
        lock.lock();
    }
}