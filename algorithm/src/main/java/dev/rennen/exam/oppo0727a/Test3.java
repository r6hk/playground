package dev.rennen.exam.oppo0727a;

import java.util.Arrays;
import java.util.Scanner;

/**
 * @author rennen.dev
 * @date 2024/8/6 11:46
 */
// TODO
public class Test3 {

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

    }
}
