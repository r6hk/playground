package dev.rennen.exam.dw0319;

import java.util.Scanner;

/**
 * @author rennen.dev
 * @date 2024/8/26 15:59
 */
public class Test2 {

    static int n, target;
    static int[] nums;
    static final int INF = 0x3f3f3f;

    public static void main(String[] args) {
        try (Scanner sc = new Scanner(System.in)) {
            n = sc.nextInt();
            target = sc.nextInt();
            nums = new int[n];
            for (int i = 0; i < nums.length; i++) {
                nums[i] = sc.nextInt();
            }
        }
        int[] dp = new int[target + 1];
        for (int i = 1; i <= target; i++) {
            dp[i] = INF;
            for (int j = n - 1; j >= 0; j--) {
                if (nums[j] <= i) {
                    dp[i] = Math.min(dp[i], dp[i - nums[j]] + 1);
                }
            }
        }
        String res = dp[target] == INF ? "No solution" : String.valueOf(dp[target]);
        System.out.println(res);
    }
}
