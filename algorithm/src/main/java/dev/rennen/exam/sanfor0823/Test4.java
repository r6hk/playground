package dev.rennen.exam.sanfor0823;

import java.util.*;

public class Test4 {

    static int m = 0, n = 0;
    static Set<String> borders = new HashSet<>();
    static int money1 = 0;
    static int money2 = 0;
    static int money2Max = 0;

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int[][] grid = new int[1000][1000];
        while (in.hasNextLine()) {
            String t = in.nextLine();
            t = t.substring(1, t.length() - 1);
            String[] numsString = t.split(",");
            if (n == 0) n = numsString.length;
            for (int i = 0; i < n; i++) {
                grid[m][i] = Integer.parseInt(numsString[i].trim());
            }
            m++;
        }
        dfs(grid, 0, 0, true);
        for (String border : borders) {
            String[] numsString = border.split(",");
            money2 = 0;
            int i = Integer.parseInt(numsString[0]);
            int j = Integer.parseInt(numsString[1]);
            grid[i][j] = 0;
            dfs(grid, i, j, false);
            money2Max = Math.max(money2, money2Max);
        }
        System.out.println(money1 + money2Max);
    }

    static void dfs(int[][] grid, int i, int j, boolean powerUsed) {
        if (i < 0 || i >= m || j < 0 || j >= n) return;
        if (grid[i][j] == -2) return;
        if (grid[i][j] == -1) {
            borders.add(i + "," + j);
        }
        if (grid[i][j] >= 0) {
            if (powerUsed) {
                money1 += grid[i][j];
            } else {
                money2 += grid[i][j];
            }
            grid[i][j] = -2;
            dfs(grid, i - 1, j, powerUsed);
            dfs(grid, i + 1, j, powerUsed);
            dfs(grid, i, j - 1, powerUsed);
            dfs(grid, i, j + 1, powerUsed);
        }
    }
}