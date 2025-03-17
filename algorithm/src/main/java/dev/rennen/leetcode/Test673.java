package dev.rennen.leetcode;

import java.util.Arrays;

/**
 * <br/>
 * 2025/3/7 20:36
 *
 * @author rennen.dev
 */
public class Test673 {
    public int findNumberOfLIS(int[] nums) {
        int n = nums.length;
        int[] dp = new int[n];
        int[] count = new int[n];
        int maxLength = 1;
        int maxCount = 1;
        Arrays.fill(dp, 1);
        Arrays.fill(count, 1);
        for (int i = 1; i < nums.length; i++) {
            for (int j = i - 1; j >= 0; j--) {
                if (nums[j] < nums[i]) {
                    int t = dp[j] + 1;
                    if (t == dp[i]) {
                        count[i]++;
                    } else if (t > dp[i]) {
                        dp[i] = t;
                        count[i] = 1;
                    }
                }
            }
            if (dp[i] > maxLength) {
                maxLength = dp[i];
                maxCount = count[i];
            } else if (dp[i] == maxLength) {
                maxCount += count[i];
            }
        }
        return maxCount;
    }

    public static void main(String[] args) {
        System.out.println(new Test673().findNumberOfLIS(new int[]{1, 2, 4, 3, 5, 4, 7, 2}));
    }
}