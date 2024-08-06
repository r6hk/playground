package dev.rennen.exam.oppo0727a;


import java.util.Arrays;
import java.util.Scanner;

/**
 * @author rennen.dev
 * @date 2024/8/6 11:10
 */
public class Test2 {

    static int n;
    static int[] nums1;
    static int[] nums2;


    public static void main(String[] args) {
        try (Scanner sc = new Scanner(System.in)) {
            n = sc.nextInt();
            nums1 = new int[n];
            for (int i = 0; i < nums1.length; i++) {
                nums1[i] = sc.nextInt();
            }
            nums2 = new int[n];
            for (int i = 0; i < nums2.length; i++) {
                nums2[i] = sc.nextInt();
            }
        }
        Arrays.sort(nums1);
        Arrays.sort(nums2);
        int res = 0;
        for (int i = 0; i < nums1.length; i++) {
            int t = Arrays.binarySearch(nums2, nums1[i]);
            if (t >= 0) {
                res = Math.max(res, i + 1 + n - t);
            } else {
                res = Math.max(res, t + 1 + n + i + 1);
            }
        }
        System.out.println(res == 0 ? -1 : res);
    }
}
