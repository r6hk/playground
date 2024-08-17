package dev.rennen.exam.meituan0810;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

/**
 * @author rennen.dev
 * @date 2024/8/17 19:36
 */
public class Test2 {

    static int t;

    public static void main(String[] args) {
        try (Scanner sc = new Scanner(System.in)) {
            t = sc.nextInt();
            for (int i = 0; i < t; i++) {
                int n = sc.nextInt();
                int k = sc.nextInt();
                int x = sc.nextInt();
                // k: 数字  v：数字最后一次出现的位置
                Map<Integer, Integer> index = new HashMap<>();
                for (int j = 0; j < n; j++) {
                    index.put(sc.nextInt(), j);
                }
                int mex = 0;
                while (index.containsKey(mex)) mex++;
                int choice = mex * x;
                for (int j = 0; j < mex; j++) {
                    // 感觉这种做法类似一种贪心，直觉上是对的，但是证明可能有点复杂
                    choice = Math.min(choice, (index.get(j) + 1) * k + j * x);
                }
                System.out.println(choice);
            }
        }

    }
}
