package dev.rennen.leetcode;

/**
 * @author rennen.dev
 * @date 2025/10/25 20:40
 */
public class Test2765 {

    public static void main(String[] args) {
        int[] nums = {2, 3, 4, 3, 4};
        System.out.println(alternatingSubarray(nums));
    }

    public static int alternatingSubarray(int[] nums) {
        int n = nums.length;
        int res = -1;
        for (int i = 0; i < n; i++) {
            int j;
            for (j = i + 1; j < n; j++) {
                int delta = (j - i) % 2 == 0 ? -1 : 1;
                if (nums[j] - nums[j - 1] != delta) break;
            }
            if (j - i >= 2) res = Math.max(res, j - i);
        }
        return res;
    }
}
