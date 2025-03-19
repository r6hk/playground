package dev.rennen.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * <br/>
 * 2025/3/19
 *
 * @author rennen.dev
 */
public class Test930 {
    public int numSubarraysWithSum(int[] nums, int goal) {
        return atLeast(nums, goal) - atLeast(nums, goal + 1);
    }

    private int atLeast(int[] nums, int goal) {
        int n = nums.length;
        int res = 0;
        int oneCount = 0;
        for (int l = 0, r = 0; r< n; r++) {
            oneCount += (nums[r] == 1 ? 1 : 0);
            while (oneCount >= goal) {
                oneCount -= (nums[l++] == 1 ? 1 : 0);
            }
            res += l;
        }
        var list = List.of()
        return res;
    }

    public static void main(String[] args) {
        System.out.println(new Test930().numSubarraysWithSum(new int[]{1, 0, 1, 0, 1}, 2));
    }
}