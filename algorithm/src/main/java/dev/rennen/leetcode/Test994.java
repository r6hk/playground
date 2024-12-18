package dev.rennen.leetcode;

import java.util.LinkedList;

/**
 * @author rennen.dev
 * @date 2024/12/6 9:54
 */
public class Test994 {


    static int[] x = new int[]{0, 1, 0, -1};
    static int[] y = new int[]{1, 0, -1, 0};

    public static int orangesRotting(int[][] grid) {
        LinkedList<int[]> queue = new LinkedList<>();
        int remain = 0; // 还未腐烂的橘子
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == 2) {
                    queue.add(new int[]{i, j});
                } else if (grid[i][j] == 1) {
                    remain++;
                }
            }
        }
        if (remain == 0) return 0;
        int res = 0;
        while (!queue.isEmpty()) {
            for (int t = queue.size(); t > 0; t--) {
                int[] xy = queue.removeFirst();
                for (int i = 0; i < 4; i++) {
                    int newX = xy[0] + x[i];
                    int newY = xy[1] + y[i];
                    if (newX >= 0
                            && newX < grid.length
                            && newY >= 0
                            && newY < grid[0].length
                            && grid[newX][newY] == 1) {
                        queue.add(new int[]{newX, newY});
                        grid[newX][newY] = 2;
                        remain--;
                    }
                }

            }
            res++;
        }
        return remain == 0 ? res : -1;
    }

    public static void main(String[] args) {
        int[][] grid = new int[][]{{2, 1, 1}, {1, 1, 0}, {0, 1, 1}};
        System.out.println(orangesRotting(grid));
    }
}
