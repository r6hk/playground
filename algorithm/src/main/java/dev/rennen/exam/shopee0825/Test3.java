package dev.rennen.exam.shopee0825;

import java.util.Arrays;

/**
 * @author rennen.dev
 * @date 2024/8/28 11:46
 */
public class Test3 {

    public static void main(String[] args) {
        int[] goods = new int[]{24, 8, 3, 12, 7, 9, 7};
        int v = 100;
        System.out.println(bestFit(goods, v));
    }

    static int bestFit(int[] goods, int v) {
        int[] dp = new int[v + 1];
        for (int i : goods) {
            for (int j = v; j >= i; j--) {
                dp[j] = Math.max(dp[j], dp[j - i] + i);
            }
        }
        System.out.println(Arrays.toString(dp));
        return dp[v];
    }

}
