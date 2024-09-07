package dev.rennen;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author rennen.dev
 * @date 2024/8/7 19:04
 */
public class Main {
    public static void main(String[] args) {
        Map<Integer, Integer> map = new ConcurrentHashMap<>();
        map.put(null, null);
        System.out.println(map.get(null));
        System.out.println(map.get(1));

        map.clear();
        System.out.println(map.get(null));
        System.out.println(map.containsKey(null));

        map.put(1, null);
        System.out.println(map.get(1));
        System.out.println(map.containsKey(1));
    }
}