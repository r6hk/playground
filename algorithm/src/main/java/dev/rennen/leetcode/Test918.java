package dev.rennen.leetcode;

/**
 * <br/>
 * 2025/3/6 21:44
 *
 * @author rennen.dev
 */
public class Test918 {

    public int maxSubarraySumCircular(int[] nums) {
        int n = nums.length;
        int[] dpLeft = new int[n];
        int[] maxIndex = new int[n];
        dpLeft[0] = nums[0];
        int max = nums[0];
        for (int i = 1; i < n; i++) {
            dpLeft[i] = Math.max(dpLeft[i - 1] + nums[i], nums[i]);
            if (dpLeft[i] > max) {
                maxIndex[i] = i;
                max = dpLeft[i];
            } else {
                maxIndex[i] = maxIndex[i - 1];
            }
        }
        int[] dpRight = new int[n];
        dpRight[n - 1] = nums[n - 1];
        int res = nums[n - 1] + (n - 2 >= 0 ? dpRight[n - 2] + dpLeft[maxIndex[n - 2]] : 0);
        for (int i = n - 2; i >= 0; i--) {
            dpRight[i] = Math.max(dpRight[i + 1] + nums[i], nums[i]);
            res = Math.max(res, i != 0 ? dpRight[i] + dpLeft[maxIndex[i - 1]] : dpRight[i]);
        }
        return res;
    }

    public static void main(String[] args) {
        Test918 test = new Test918();
//
//        // Test case 1: [1,-2,3,-2]
//        int[] nums1 = {1, -2, 3, -2};
//        System.out.println("Test case 1: " + test.maxSubarraySumCircular(nums1)); // Expected: 3

        // Test case 2: [5,-3,5]
        int[] nums2 = {5, -3, 5};
        System.out.println("Test case 2: " + test.maxSubarraySumCircular(nums2)); // Expected: 10

//        // Test case 3: [-3,-2,-3]
//        int[] nums3 = {-3, -2, -3};
//        System.out.println("Test case 3: " + test.maxSubarraySumCircular(nums3)); // Expected: -2
//
//        // Test case 4: [3,-1,2,-1]
//        int[] nums4 = {3, -1, 2, -1};
//        System.out.println("Test case 4: " + test.maxSubarraySumCircular(nums4)); // Expected: 4
    }
}
