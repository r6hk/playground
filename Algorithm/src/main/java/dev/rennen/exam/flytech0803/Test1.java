package dev.rennen.exam.flytech0803;

import java.util.Scanner;

/**
 * @author rennen.dev
 * @date 2024.08.05
 */
public class Test1 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] nums = new int[n + 1];
        for (int i = 1; i < nums.length; i++) {
            nums[i] = sc.nextInt();
        }
        int res = 0;
        for (int i = 1; i <= n; i++) {
            while (i != nums[i]) {
                swap(nums, nums[i], nums[nums[i]]);
                res++;
            }
        }
        System.out.println(res);
    }

    private static void swap(int[] nums, int i, int j) {
        int t = nums[i];
        nums[i] = nums[j];
        nums[j] = t;
    }
}
