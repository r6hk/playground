package dev.rennen.exam.microfun0811;

import java.util.Scanner;

/**
 * @author rennen.dev
 * @date 2024/8/12 9:21
 */
public class Test1 {

    static int n, s;
    static int[] nums;

    public static void main(String[] args) {
        try (Scanner sc = new Scanner(System.in)) {
            n = sc.nextInt();
            s = sc.nextInt();
            nums = new int[n];
            for (int i = 0; i < n; i++) {
                nums[i] = sc.nextInt();
            }
        }
        int[] dp = new int[s + 1];
        dp[0] = 1;
        for (int i = 0; i < n; i++) {
            for (int j = s; j >= nums[i]; j--) {
                dp[j] += dp[j - nums[i]];
            }
        }
        System.out.println(dp[s]);
    }
}
