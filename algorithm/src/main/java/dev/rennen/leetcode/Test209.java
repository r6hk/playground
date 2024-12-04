package dev.rennen.leetcode;

/**
 * @author rennen.dev
 * @date 2024/10/26 16:16
 */
public class Test209 {

    public static int minSubArrayLen(int target, int[] nums) {
        // 前缀和二分
        int n = nums.length;
        int[] preSum = new int[n + 1];
        for (int i = 1; i < n + 1; i++) {
            preSum[i] = preSum[i - 1] + nums[i - 1];
        }
        int res = 0x3f3f3f;
        for (int i = 0; i < n + 1; i++) {
            int t = preSum[i];
            int j = binarySearch(preSum, t + target);
            if (j >= 0 && j <= n) res = Math.min(res, j - i);
        }
        return res == 0x3f3f3f ? 0 : res;
    }

    // 0 2 5 6 8 12 15
    private static int binarySearch(int[] nums, int target) {
        int l = 0;
        int r = nums.length - 1;
        while (l <= r) {
            int m = l + (r - l) / 2;
            if (nums[m] >= target) r = m - 1;
            else l = m + 1;
        }
        return l;
    }

    public static void main(String[] args) {
        System.out.println(minSubArrayLen(4, new int[]{1, 4, 4}));
    }
}
