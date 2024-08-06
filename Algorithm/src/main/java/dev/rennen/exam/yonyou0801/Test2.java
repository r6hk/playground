package dev.rennen.exam.yonyou0801;

import java.util.Scanner;

/**
 * @author rennen.dev
 * @date 2024/8/5 17:46
 */
public class Test2 {

    static final Scanner SC = new Scanner(System.in);

    public static void main(String[] args) {
        int limit, power;
        limit = SC.nextInt();
        power = SC.nextInt();
        int[][] dp = new int[power + 1][power + 1];
        for (int i = 1; i <= power; i++) {
            dp[i][1] = i;
        }
        int max = 0;
        // 感觉可能有超时的风险
        for (int i = 2; i <= power; i++) {
            for (int j = i; j <= power; j++) {
                for (int k = 1; k <= j; k++) {
                    dp[j][i] = Math.max(dp[j][i], dp[j - k][i - 1] * k);
                    max = Math.max(max, dp[j][i]);
                }
            }
        }
        System.out.println(max >= limit);
    }
}
