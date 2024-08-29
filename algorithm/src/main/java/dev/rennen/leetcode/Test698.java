package dev.rennen.leetcode;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

/**
 * @author rennen.dev
 * @date 2024/8/24 11:31
 */
public class Test698 {

    public static void main(String[] args) {

    }

    public static boolean canPartitionKSubsets(int[] nums, int k) {
        int sum = Arrays.stream(nums).sum();
        if (sum % k != 0) return false;
        Arrays.sort(nums);
        if (nums[nums.length - 1] > sum / k) return false;
        Map<Integer, Integer> count = new HashMap<>();
        for (int num : nums) {
            count.put(num, count.getOrDefault(num, 0) + 1);
        }
        for (int i = nums.length - 1; i >= 0; i--) {
            int t = nums[i];
            if (count.getOrDefault(t, 0) == 0) continue;

        }
        return false;
    }
}
