package dev.rennen.template;

import java.util.Arrays;

/**
 * @author rennen.dev
 * @date 2024/10/2 10:24
 */
public class HeapSort {

    public static void main(String[] args) {
        int[] nums = new int[]{44, 251, 511, 11, 35, 62, 16, 64, 11, 431, 22, 616};
        heapSort(nums);
        System.out.println(Arrays.toString(nums));
    }

    private static void heapSort(int[] nums) {
        int n = nums.length;
        for (int i = n / 2 - 1; i >= 0; i--) {
            heapify(nums, i, n - 1);
        }
        for (int i = n - 1; i > 0; i--) {
            swap(nums, 0, i);
            heapify(nums, 0, i - 1);
        }
    }

    private static void heapify(int[] nums, int begin, int end) {
        int i = begin;
        int j = i * 2 + 1;
        while (j <= end) {
            if (j + 1 <= end && nums[j + 1] > nums[j]) {
                j++;
            }
            if (nums[i] < nums[j]) {
                swap(nums, i, j);
                i = j;
                j = i * 2 + 1;
            } else break;
        }
    }

    private static void swap(int[] nums, int i, int j) {
        int t = nums[i];
        nums[i] = nums[j];
        nums[j] = t;
    }
}
