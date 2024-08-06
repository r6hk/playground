package dev.rennen.template;

/**
 * @author rennen.dev
 * @date 2024/8/6 11:58
 */
public class BinarySearch {

    public static void main(String[] args) {
        int[] nums = new int[]{1, 3, 3, 3, 5};
        System.out.println(binarySearchLeftBound(nums, 2));
        System.out.println(binarySearchLeftBound(nums, 3));
        System.out.println(binarySearchRightBound(nums, 2));
        System.out.println(binarySearchRightBound(nums, 3));
    }

    /**
     * 找不小于 target 的最左边的数
     */
    private static int binarySearchLeftBound(int[] nums, int target) {
        int l = 0, r = nums.length - 1;
        while (l <= r) {
            int m = l + (r - l) / 2;
            if (nums[m] >= target) {
                r = m - 1;
            } else {
                l = m + 1;
            }
        }
        return l;
    }

    /**
     * 找不大于 target 的最右边的数
     */
    private static int binarySearchRightBound(int[] nums, int target) {
        int l = 0, r = nums.length - 1;
        while (l <= r) {
            int m = l + (r - l) / 2;
            if (nums[m] <= target) {
                l = m + 1;
            } else {
                r = m - 1;
            }
        }
        return r;
    }
}
