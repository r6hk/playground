package dev.rennen.leetcode;

import java.util.HashSet;
import java.util.Set;

/**
 * <br/>
 * 2025/2/26 17:22
 *
 * @author rennen.dev
 */
public class Test827 {
    int[] p;
    int[] size;
    int[][] direction = new int[][]{{0, -1}, {-1, 0}, {0, 1}, {1, 0}};
    int n;

    public int largestIsland(int[][] grid) {
        n = grid.length;
        p = new int[n * n + 2];
        size = new int[n * n + 2];
        for (int i = 0; i < n * n; i++) {
            p[i] = i;
            size[i] = 1;
        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1) {
                    for (int[] dir : direction) {
                        int t1 = i + dir[0];
                        int t2 = j + dir[1];
                        if (t1 >= 0 && t1 < n && t2 >= 0 && t2 < n && grid[t1][t2] == 1) {
                            union(convert(t1, t2), convert(i, j));
                        }
                    }
                }
            }
        }
        int ans = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                // 枚举翻转
                if (grid[i][j] == 0) {
                    int tmp = 1;
                    Set<Integer> seen = new HashSet<>();
                    for (int[] dir : direction) {
                        int t1 = i + dir[0];
                        int t2 = j + dir[1];
                        if (t1 >= 0 && t1 < n && t2 >= 0 && t2 < n && grid[t1][t2] == 1) {
                            int t = find(convert(t1, t2));
                            if (seen.contains(t)) continue;
                            seen.add(t);
                            tmp += size[t];
                        }
                    }
                    ans = Math.max(ans, tmp);
                } else {
                    ans = Math.max(ans, size[find(convert(i, j))]);
                }
            }
        }
        return ans;
    }

    private void union(int a, int b) {
        int rootA = find(a), rootB = find(b);
        if (rootA == rootB) return;
        if (size[rootA] > size[rootB]) union(b, a);
        else {
            size[rootB] += size[rootA];
            p[rootA] = p[rootB];
        }

    }

    private int find(int a) {
        if (p[a] != a) {
            p[a] = find(p[a]);
        }
        return p[a];
    }

    private int convert(int i, int j) {
        return i * n + j;
    }

    public static void main(String[] args) {
        Test827 test827 = new Test827();
        int res = test827.largestIsland(new int[][]{{1, 1}, {1, 0}});
        System.out.println("res = " + res);
    }
}