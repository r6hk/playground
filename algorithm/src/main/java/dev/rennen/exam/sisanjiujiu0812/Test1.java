package dev.rennen.exam.sisanjiujiu0812;

import java.net.Authenticator;
import java.util.Scanner;

/**
 * @author rennen.dev
 * @date 2024/8/17 16:36
 */
public class Test1 {

    static String input;

    public static void main(String[] args) {
        try (Scanner sc = new Scanner(System.in)) {
            input = sc.nextLine();
        }
        String[] split = input.split(",");
        int layer = 0;
        int res = 0;
        for (String s : split) {
            StringBuilder sb = new StringBuilder();
            boolean added = false;
            for (char c : s.toCharArray()) {
                if (c == '[') layer++;
                else if (c == ']' && !added) {
                    added = true;
                    res += (Integer.parseInt(sb.toString()) * layer);
                    layer--;
                }
                else sb.append(c);
            }
            if (!added) res += (Integer.parseInt(sb.toString()) * layer);
        }
        System.out.println(res);
    }

    static boolean isNum(char c) {
        return c >= '0' && c <= '9';
    }
}
