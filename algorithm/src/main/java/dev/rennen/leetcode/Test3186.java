package dev.rennen.leetcode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;

/**
 * @author rennen.dev
 * @date 2025/10/23 22:41
 */
public class Test3186 {
    public static void main(String[] args) {
        System.out.println(maximumTotalDamage(new int[]{5,9,2,10,2,7,10,9,3,8}));
        // 应该选择两个 2 不是一个 3
    }

    public static long maximumTotalDamage(int[] power) {
        var map = new HashMap<Integer, Integer>();
        for (int p : power) {
            map.merge(p, 1, Integer::sum);
        }
        var list = new ArrayList<>(map.keySet());
        list.sort(Comparator.naturalOrder());
        var size = list.size();
        long[] dp = new long[size + 1];
        for (int i = 1; i <= size; i++) {
            int num = list.get(i - 1);
            long bonus = (long) num * map.get(num);
            int preDpIndex = Collections.binarySearch(list, num - 3);
            if (preDpIndex < 0) {
                preDpIndex = -preDpIndex - 2;
            }
            dp[i] = Math.max(dp[i - 1], dp[preDpIndex + 1] + bonus);
        }
        return dp[size];
    }
}
