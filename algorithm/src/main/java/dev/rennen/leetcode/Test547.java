package dev.rennen.leetcode;

import java.util.HashSet;
import java.util.Set;

/**
 * @author rennen.dev
 * @date 2024/9/16 21:44
 */
public class Test547 {

    public static void main(String[] args) {
        Solution solution = new Solution();

        int[][] isConnected = {
                {1, 0, 0, 1},
                {0, 1, 1, 0},
                {0, 1, 1, 1},
                {1, 0, 1, 1}
        };

        int result = solution.findCircleNum(isConnected);
        System.out.println("省份数量: " + result);
    }

}

class Solution {
    public int findCircleNum(int[][] isConnected) {
        int n = isConnected.length;
        int[] parent = new int[10];
        for (int i = 0; i < n; i++) {
            parent[i] = i;
        }
        for (int i = 0; i < n; i++) {
            for (int j = i; j < n; j++) {
                if (isConnected[i][j] == 1) {
                    union(i, j, parent);
                }
            }
        }
        Set<Integer> province = new HashSet<>(n);
        for (int i = 0; i < n; i++) {
            if (parent[i] != -1) {
                province.add(parent[i]);
            }
        }
        return province.size();
    }

    int find(int x, int[] parent) {
        while (parent[x] != x) {
            x = parent[x];
        }
        return x;
    }

    void union(int a, int b, int[] parent) {
        int aIndex = find(a, parent);
        int bIndex = find(b, parent);
        if (aIndex < bIndex) {
            parent[bIndex] = aIndex;
        } else {
            parent[aIndex] = bIndex;
        }
    }
}