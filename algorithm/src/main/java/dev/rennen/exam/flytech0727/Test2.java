package dev.rennen.exam.flytech0727;

import java.util.Scanner;

/**
 * @author rennen.dev
 * @date 2024/8/6 15:41
 */
public class Test2 {

    public static void main(String[] args) {
        int num;
        try (Scanner sc = new Scanner(System.in)) {
            num = sc.nextInt();
        }
        int res = 0;
        for (int i = 2; i <= 36; i++) {
            res = Math.max(res, countOne(num, i));
        }
        System.out.println(res);
    }

    private static int countOne(int num, int i) {
        int count = 0;
        while (num != 0) {
            if (num % i == 1) {
                count++;
            }
            num /= i;
        }
        return count;
    }


}
