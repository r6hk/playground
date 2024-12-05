package dev.rennen.leetcode;

import java.util.LinkedList;

/**
 * @author rennen.dev
 * @date 2024/12/5 9:27
 */
public class Test909 {

    public static void main(String[] args) {
        int[][] board = new int[][]{{-1,-1,19,10,-1},{2,-1,-1,6,-1},{-1,17,-1,19,-1},{25,-1,20,-1,-1},{-1,-1,-1,-1,15}};
        System.out.println(snakesAndLadders(board));

    }

    public static int snakesAndLadders(int[][] board) {
        int n = board.length;
        int all = n * n;
        boolean[] visited = new boolean[n * n + 1];
        int[] converted = convert(board);
        LinkedList<int[]> queue = new LinkedList<>();
        queue.add(new int[]{1, 0});
        while (!queue.isEmpty()) {
            int[] arr = queue.removeFirst();
            int t = arr[0];
            int layer = arr[1];
            if (t == all)
                return layer;
            for (int i = t + 1; i <= Math.min(t + 6, all); i++) {
                int t2 = i;
                if (converted[i] != -1) t2 = converted[i];
                if (!visited[t2]) {
                    visited[t2] = true;
                    queue.add(new int[]{t2, layer + 1});
                }
            }
        }
        return -1;
    }

    private static int[] convert(int[][] board) {
        int n = board.length;
        int nums = board.length * board.length + 1;
        int[] converted = new int[nums];
        int count = 1;
        for (int i = n - 1; i >= 0; i--) {
            int adder = (n - 1 - i) % 2 == 1 ? -1 : 1;
            int j = (n - 1 - i) % 2 == 1 ? n - 1 : 0;
            for (; j >= 0 && j < n; j += adder) {
                converted[count++] = board[i][j];
            }
        }
        return converted;
    }
}
