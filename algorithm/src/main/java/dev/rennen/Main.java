package dev.rennen;

public class Main {
    public static void main(String[] args) {
        int[] nums = new int[]{-2, 3, -4};
        System.out.println(maxProduct(nums));
    }

    public static int maxProduct(int[] nums) {
        int[] dp1 = new int[nums.length];
        int[] dp2 = new int[nums.length];
        dp1[0] = nums[0];
        dp2[0] = nums[0];
        int res = dp1[0];
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] > 0) {
                dp1[i] = Math.max(dp1[i - 1] * nums[i], nums[i]);
                dp2[i] = Math.min(dp2[i - 1] * nums[i], nums[i]);
            } else {
                dp1[i] = Math.max(dp2[i - 1] * nums[i], nums[i]);
                dp2[i] = Math.min(dp1[i - 1] * nums[i], nums[i]);
            }
            res = Math.max(res, dp1[i]);
        }
        return res;
    }
}