package dev.rennen.exam.flytech0803;

import java.util.Scanner;

/**
 * @author rennen.dev
 * @date 2024/8/5 9:37
 */
public class Test2 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        String[] s = new String[n];
        for (int i = 0; i < n; i++) {
            s[i] = sc.next();
        }
        for (String t : s) {
            String[] split = t.split(":");
            int hour = Integer.parseInt(split[0]);
            int minute = Integer.parseInt(split[1]);
            hour = hour - 8 < 0 ? hour + 16 : hour - 8;
            System.out.println(addZero(hour) + ":" + addZero(minute));
        }
    }

    private static String addZero(int t) {
        if (t < 10) {
            return "0" + t;
        }
        return t + "";
    }
}
