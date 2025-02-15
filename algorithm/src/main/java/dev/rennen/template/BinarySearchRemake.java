package dev.rennen.template;

/**
 * @author rennen.dev
 * @date 2024/12/23 10:09
 */
public class BinarySearchRemake {

    public static void main(String[] args) {
        int[] nums = {1, 2 ,3, 5, 5, 5, 7, 8, 9};
        for (int i = 1; i <= 9; i++) {
            System.out.println("i = " + i);
            System.out.println("leftBound(nums, i) = " + leftBound(nums, i));
            System.out.println("rightBound(nums, i) = " + rightBound(nums, i));
        }
    }

    private static int leftBound(int[] nums, int target) {
        // 定义：不小于 target 的最左边的元素下标
        int l = 0, r = nums.length - 1;
        while (l <= r) {
            int m = l + (r - l) / 2;
            if (nums[m] >= target) r = m - 1;
            else l = m + 1;
        }
        return l;
    }

    private static int rightBound(int[] nums, int target) {
        int l = 0, r = nums.length - 1;
        while (l <= r) {
            int m = l + (r - l) / 2;
            if (nums[m] <= target) l = m + 1;
            else r = m - 1;
        }
        return r;
    }
}
