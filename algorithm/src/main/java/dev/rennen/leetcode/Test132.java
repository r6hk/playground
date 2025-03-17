package dev.rennen.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * <br/>
 * 2025/3/4 21:21
 *
 * @author rennen.dev
 */
public class Test132 {

    int[][] isPartitioning;
    int n;
    List<Integer> tmp = new ArrayList<>();


    public int minCut(String s) {
        n = s.length();
        isPartitioning = new int[n][n];
        for (int i = 0; i < n; i++) {
            isPartitioning[i][i] = 1;
        }
        for (int L = 2; L <= n; L++) {
            for (int i = 0; i < n - L + 1; i++) {
                if (s.charAt(i) == s.charAt(i + L - 1)) {
                    if (L == 2) {
                        isPartitioning[i][i + L - 1] = 1;
                    } else {
                        isPartitioning[i][i + L - 1] = isPartitioning[i + 1][i + L - 2];
                    }
                }
            }
        }
        tmp.add(0);
        for (int i = 1; i < n; i++) {
            boolean flag = false;
            for (Integer j : tmp) {
                if (isPartitioning[j][i] == 1) {
                    flag = true;
                    for (int k = tmp.size() - 1; k >= 0; k--) {
                        if (tmp.get(k) > j) tmp.remove(k);
                    }
                    break;
                }
            }
            if (!flag) {
                tmp.add(i);
            }
        }
        return tmp.size() - 1;
    }

    public static void main(String[] args) {
        Test132 test132 = new Test132();
        System.out.println(test132.minCut("aaabaa"));
    }
}
