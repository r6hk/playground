package dev.rennen.exam.flytech0713;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * @author rennen.dev
 * @date 2024/8/6 16:22
 */
public class Test1 {

    public static void main(String[] args) {
        try (Scanner sc = new Scanner(System.in)) {
            int t = sc.nextInt();
            for (int i = 0; i < t; i++) {
                sc.nextInt();
                calculate(sc.next());
            }
        }
    }

    private static void calculate(String line) {
        char[] ch = line.toCharArray();
        int[] dp = new int[ch.length];
        dp[0] = -1;
        for (int i = 1; i < ch.length; i++) {
            if (ch[i] == ch[i - 1]) {
                dp[i] = dp[i -1];
            } else {
                dp[i] = i;
            }
        }
        for (int i : dp) {
            System.out.print(i + " ");
        }
        System.out.println();
    }
}
