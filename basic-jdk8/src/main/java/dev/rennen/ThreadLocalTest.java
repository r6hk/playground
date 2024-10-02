package dev.rennen;

import java.lang.ref.WeakReference;
import java.util.HashSet;
import java.util.Set;

/**
 * @author rennen.dev
 * @date 2024/8/23 11:14
 */
public class ThreadLocalTest {

    private static ThreadLocal<Integer> integerThreadLocal = new ThreadLocal<>();

    public static void main(String[] args) {
        integerThreadLocal.set(1);
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println(integerThreadLocal.get());
            }
        });
        t.start();
    }
}
