package dev.rennen.leetcode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * 2025/1/17 11:44
 *
 * @author rennen.dev
 */
public class Test85 {

    public static void main(String[] args) {
        char[][] matrix = {
                {'1', '0', '1', '0', '0'},
                {'1', '0', '1', '1', '1'},
                {'1', '1', '1', '1', '1'},
                {'1', '0', '0', '1', '0'}
        };
        Test85 solution = new Test85();
        int res = solution.maximalRectangle(matrix);
        System.out.println(res);
    }

    public int maximalRectangle(char[][] matrix) {
        int res = 0;
        List<Integer> heights = new ArrayList<>();
        heights.add(0);
        for (char c : matrix[0]) {
            heights.add(c == '1' ? 1 : 0);
        }
        heights.add(0);
        res = Math.max(res, calculate(heights));
        for (int i = 1; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                if (matrix[i][j] == '0') {
                    heights.set(j + 1, 0);
                } else {
                    heights.set(j + 1, heights.get(j + 1) + 1);
                }
            }
            res = Math.max(res, calculate(heights));
        }
        return res;
    }

    private int calculate(List<Integer> heights) {
        int res = 0;
        LinkedList<Integer> stack = new LinkedList<>();
        stack.addLast(0);
        for (int i = 1; i < heights.size(); i++) {
            while (heights.get(i) < heights.get(stack.peekLast())) {
                int curHeight = heights.get(stack.removeLast());
                int curWidth = i - stack.peekLast() - 1;
                res = Math.max(res, curHeight * curWidth);
            }
            stack.addLast(i);
        }
        return res;
    }
}
