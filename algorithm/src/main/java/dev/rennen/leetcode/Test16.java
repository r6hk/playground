package dev.rennen.leetcode;

/**
 * <br/>
 * 2025/3/24
 *
 * @author rennen.dev
 */
public class Test16 {
    public int threeSumClosest(int[] nums, int target) {
        int closestSum = nums[0] + nums[1] + nums[2];
        int n = nums.length;
        for (int i = 0; i < n - 2; i++) {
            int j = i + 1, k = n - 1;
            while (j < k) {
                int sum = nums[i] + nums[j] + nums[k];
                if (sum == target) return sum;
                else {
                    if (Math.abs(sum - target) < Math.abs(closestSum - target)) closestSum = sum;
                    if (sum < target) j++;
                    else k--;
                }
            }
        }
        return closestSum;
    }

    public static void main(String[] args) {
        var test16 = new Test16();
        System.out.println(test16.threeSumClosest(new int[]{-84, 92, 26, 19, -7, 9, 42, -51, 8, 30, -100, -13, -38}, 78));
    }
}