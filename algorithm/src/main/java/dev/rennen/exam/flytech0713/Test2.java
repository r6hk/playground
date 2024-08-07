package dev.rennen.exam.flytech0713;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * @author rennen.dev
 * @date 2024/8/6 16:40
 */
public class Test2 {

    static int n, k;
    static Map<Integer, Integer> times = new HashMap<>();

    public static void main(String[] args) {
        try (Scanner sc = new Scanner(System.in)) {
            n = sc.nextInt();
            k = sc.nextInt();
            for (int i = 0; i < n; i++) {
                int t = sc.nextInt();
                times.put(t, times.getOrDefault(t, 0) + 1);
            }
        }
        int res = times.entrySet().stream()
                .filter(t -> t.getValue() <= k)
                .mapToInt(Map.Entry::getKey)
                .min()
                .orElse(-1);
        System.out.println(res);
    }
}
