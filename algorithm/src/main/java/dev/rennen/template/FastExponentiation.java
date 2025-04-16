package dev.rennen.template;

import java.util.Scanner;

/**
 * <br/>
 * 2025/4/2
 *
 * @author rennen.dev
 */
public class FastExponentiation {
    static final int MOD = (int) 1e9 + 7;

    public static void main(String[] args) {
        System.out.println(powerOfTwo(8));
    }

    // 快速幂算法实现
    public static int powerOfTwo(int n) {
        long result = 1;
        long base = 2; // 底数为2
        while (n > 0) {
            if (n % 2 == 1) {    // 如果当前位是1
                result = (result * base) % MOD; // 乘到结果中
            }
            base = (base * base) % MOD; // 底数平方
            n = n >> 1; // 右移一位（相当于除以2）
        }
        return (int) result;
    }
}