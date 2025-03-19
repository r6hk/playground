package dev.rennen.leetcode;

import java.util.Arrays;

/**
 * <br/>
 * 2025/3/17
 *
 * @author rennen.dev
 */
public class Test2962 {
    public long countSubarrays(int[] nums, int k) {
        int max = Arrays.stream(nums).max().orElse(-1);
        int count = 0;
        int n = nums.length;
        int res = 0;
        for (int l = 0, r = 0; r < n; r++) {
            count += (nums[r] == max ? 1 : 0);
            while (count >= k) {
                count -= (nums[l++] == max ? 1 : 0);
            }
            res += l;
        }
        return res;
    }
}