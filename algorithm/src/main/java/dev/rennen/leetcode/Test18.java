package dev.rennen.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * <br/>
 * 2025/3/24
 *
 * @author rennen.dev
 */
public class Test18 {
    public List<List<Integer>> fourSum(int[] nums, int target) {
        Arrays.sort(nums);
        var res = new ArrayList<List<Integer>>();
        int n = nums.length;
        for (int i = 0; i < n - 3; i++) {
            if (i > 0 && nums[i] == nums[i - 1]) continue;
            if (nums[i] + nums[i + 1] < target) break;
            for (int j = i + 1; j < n - 2; j++) {
                if (j > i + 1 && nums[j] == nums[j - 1]) continue;
                if (nums[i] + nums[j] + nums[j + 1] + nums[j + 2] > target) break;
                if (nums[i] + nums[j] + nums[n - 1] + nums[n - 2] < target) continue;
                int k = j + 1, l = n - 1;
                while (k < l) {
                    long sum = nums[i] + nums[j] + nums[k] + nums[l];
                    if (sum < target) k++;
                    else if (sum > target) l--;
                    else {
                        res.add(List.of(nums[i], nums[j], nums[k], nums[l]));
                        for (k++; k < l && nums[k] == nums[k - 1]; k++);
                        for (l--; k < l && nums[l] == nums[l + 1]; l--);
                    }
                }
            }
        }
        return res;
    }

    public static void main(String[] args) {
        var test18 = new Test18();
        System.out.println(test18.fourSum(new int[]{1, -2, -5, -4, -3, 3, 3, 5}, -11));
    }
}