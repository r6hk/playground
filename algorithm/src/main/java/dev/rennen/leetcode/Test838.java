package dev.rennen.leetcode;

import java.lang.reflect.Array;
import java.util.Arrays;

/**
 * @author rennen.dev
 * @date 2025/10/25 21:17
 */
public class Test838 {

    public static void main(String[] args) {
        Test838 test = new Test838();
        String input = ".L.R...LR..L..";
        String result = test.pushDominoes(input);
        System.out.println(input);
        System.out.println(result);
    }

    public String pushDominoes(String dominoes) {
        int n = dominoes.length();
        char[] c = new char[n + 2];
        System.arraycopy(dominoes.toCharArray(), 0, c, 1, n);
        c[0] = 'L';
        c[n + 1] = 'R';
        for (int i = 0; i < n + 1; i++) {
            char t = c[i];
            int j;
            for (j = i + 1; j < n + 1; j++) {
                if (c[j] == 'L' || c[j] == 'R') break;
            }
            if (t == 'L' && c[j] == 'L') Arrays.fill(c, i, j, 'L');
            if (t == 'R' && c[j] == 'R') Arrays.fill(c, i, j, 'R');
            if (t == 'R' && c[j] == 'L') {
                int s = j - i + 1;
                if (s > 2) {
                    if (s % 2 == 0) {
                        Arrays.fill(c, i + 1, i + s / 2, 'R');
                        Arrays.fill(c, i + s / 2, j, 'L');
                    } else {
                        int mid = i + s / 2;
                        Arrays.fill(c, i + 1, i + s / 2, 'R');
                        Arrays.fill(c, i + s / 2 + 1, j, 'L');
                    }
                }
            }
            i = j - 1;

        }
        return new String(Arrays.copyOfRange(c, 1, n + 1));
    }
}
