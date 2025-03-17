package dev.rennen.leetcode;

import lombok.val;

/**
 * <br/>
 * 2025/3/7 15:51
 *
 * @author rennen.dev
 */
public class Test790 {
    public int numTilings(int n) {
        int[][] dp = new int[n + 1][3];
        dp[1][0] = 1;
        for (int i = 2; i <= n; i++) {
            if (dp[i - 1][1] != 0)
                dp[i][0] += (dp[i - 1][1] + 1);
            if (dp[i - 1][2] != 0)
                dp[i][0] += (dp[i - 1][2] + 1);
            dp[i][0] += (dp[i - 2][0] + 1);
            dp[i][0] += (dp[i - 1][0] + 1);
            dp[i][1] = dp[i - 2][0] + 1;
            dp[i][2] = dp[i - 2][0] + 1;
        }
        return dp[n][0];
    }

    public static void main(String[] args) {
        val a = new Test790().numTilings(3);
    }
}