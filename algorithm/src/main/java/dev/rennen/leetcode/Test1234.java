package dev.rennen.leetcode;

import java.util.Arrays;

/**
 * <br/>
 * 2025/3/15 16:55
 *
 * @author rennen.dev
 */
public class Test1234 {

    private final char[] table = new char[]{'Q', 'W', 'E', 'R'};

    public int balancedString(String s) {
        int[] count = new int[4];
        char[] c = s.toCharArray();
        int n = c.length;
        for (char character : c) {
            for (int i = 0; i < 4; i++) {
                if (character == table[i]) {
                    count[i]++;
                }
            }
        }
        boolean noReplace = true;
        for (int i = 0; i < 4; i++) {
            count[i] -= n / 4;
            if (count[i] < 0) {
                count[i] = 0;
                noReplace = false;
            }
        }
        if (noReplace) return 0;
        int[] newCount = new int[4];
        int res = n;
        for (int l = 0, r = 0; r < n; r++) {
            for (int i = 0; i < 4; i++) {
                if (c[r] == table[i]) {
                    newCount[i]++;
                    break;
                }
            }
            while (compare(count, newCount)) {
                res = Math.min(res, r - l + 1);
                for (int i = 0; i < 4; i++) {
                    if (c[l] == table[i]) {
                        newCount[i]--;
                        l++;
                        break;
                    }
                }
            }
        }
        return res;
    }

    private boolean compare(int[] count, int[] newCount) {
        for (int i = 0; i < 4; i++) {
            if (count[i] != 0 && newCount[i] < count[i]) return false;
        }
        return true;
    }


    public static void main(String[] args) {
        System.out.println(new Test1234().balancedString("QQWE"));
    }

}