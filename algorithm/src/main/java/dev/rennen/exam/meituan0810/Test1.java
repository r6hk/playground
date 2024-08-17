package dev.rennen.exam.meituan0810;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author rennen.dev
 * @date 2024/8/17 19:26
 */
public class Test1 {

    static int n;
    static String right;
    static Map<Integer, Set<String>> passMap = new HashMap<>();

    public static void main(String[] args) {
        try (Scanner sc = new Scanner(System.in)) {
            n = sc.nextInt();
            right = sc.next();
            for (int i = 0; i < n; i++) {
                String t = sc.next();
                int length = t.length();
                if (!passMap.containsKey(length)) {
                    passMap.put(length, new HashSet<>());
                }
                passMap.get(length).add(t);
            }
        }
        List<Integer> keyList = passMap.keySet().stream().sorted().collect(Collectors.toList());
        int t = 0;
        for (Integer i : keyList) {
            if (i == right.length()) break;
            t += passMap.get(i).size();
        }
        int min = t + 1;
        int max = t + passMap.get(right.length()).size();
        System.out.println(min + " " + max);
    }
}
