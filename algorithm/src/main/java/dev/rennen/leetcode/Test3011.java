package dev.rennen.leetcode;

/**
 * @author rennen.dev
 * @date 2025/10/25 20:20
 */
public class Test3011 {

    public static void main(String[] args) {
        System.out.println(canSortArray(new int[]{18, 3, 8}));
    }

    public static boolean canSortArray(int[] nums) {
        int  n = nums.length;
        int lastMax = Integer.MIN_VALUE;
        for (int i = 0; i < n; i++) {
            int j;
            int count = Integer.bitCount(nums[i]);
            int thisMin = nums[i], thisMax = nums[i];
            for (j = i + 1; j < n; j++) {
                if (Integer.bitCount(nums[j]) != count) break;
                thisMin = Math.min(thisMin, nums[j]);
                thisMax = Math.min(thisMax, nums[j]);
            }
            if (thisMin < lastMax) return false;
            i = j - 1;
            lastMax = thisMax;
        }
        return true;

    }
}
