package dev.rennen;

import java.lang.ref.WeakReference;
import java.util.HashSet;
import java.util.Set;

/**
 * @author rennen.dev
 * @date 2024/8/23 11:14
 */
public class ThreadLocalTest {

    private static ThreadLocal<Integer> integerThreadLocal;

    public static void main(String[] args) {
        WeakReference<Integer> t = new WeakReference<>(1);
        Set<Integer> integers = new HashSet<>();
        integers.add(1);
        integers.contains(1);
    }
}
