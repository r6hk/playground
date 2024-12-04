package dev.rennen;

import java.io.IOException;

/**
 * @author rennen.dev
 * @date 2024/12/3 11:58
 */
public class ExceptionTest {

    public static void main(String[] args) {
        int[] nums = new int[]{1, 2, 3, 4, 5};
        for (int num : nums) {
            num = 0;
        }
        for (int num : nums) {
            System.out.println(num);
        }

        // test();
    }

    private static void test() {
        try {
            System.out.println("try");
            throw new IOException();
        } catch (Exception e) {
            System.out.println("catch");
        } finally {
            System.out.println("finally");
        }
    }
}
