package dev.rennen.leetcode;

/**
 * @author rennen.dev
 * @date 2024/11/3 16:49
 */
public class Test427 {

    public Node construct(int[][] grid) {
        return construct(grid, 0, 0, grid.length - 1, grid.length - 1);
    }

    private Node construct(int[][] grid, int ax, int ay, int bx, int by) {
        boolean val = grid[ax][ay] == 1;
        if (checkSame(grid, ax, ay, bx, by)) return new Node(val, true);
        else {
            int midx = ax + (bx - ax) / 2;
            int midy = ay + (by - ay) / 2;
            return new Node(val,
                    false,
                    construct(grid, ax, ay, midx, midy),
                    construct(grid, ax, midy + 1, midx, by),
                    construct(grid, midx + 1, ay, bx, midy),
                    construct(grid, midx + 1, midy + 1, bx, by));
        }
    }

    private boolean checkSame(int[][] grid, int ax, int ay, int bx, int by) {
        int first = grid[ax][ay];
        for (int i = ax; i <= bx; i++) {
            for (int j = ay; j <= by; j++) {
                if (grid[i][j] != first)  return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        int[][] grid = {{1,1,1,1,0,0,0,0},{1,1,1,1,0,0,0,0},{1,1,1,1,1,1,1,1},{1,1,1,1,1,1,1,1},{1,1,1,1,0,0,0,0},{1,1,1,1,0,0,0,0},{1,1,1,1,0,0,0,0},{1,1,1,1,0,0,0,0}};
        Test427 solution = new Test427();
        Node result = solution.construct(grid);
        System.out.println(result);
    }

}

class Node {
    public boolean val;
    public boolean isLeaf;
    public Node topLeft;
    public Node topRight;
    public Node bottomLeft;
    public Node bottomRight;


    public Node() {
        this.val = false;
        this.isLeaf = false;
        this.topLeft = null;
        this.topRight = null;
        this.bottomLeft = null;
        this.bottomRight = null;
    }

    public Node(boolean val, boolean isLeaf) {
        this.val = val;
        this.isLeaf = isLeaf;
        this.topLeft = null;
        this.topRight = null;
        this.bottomLeft = null;
        this.bottomRight = null;
    }

    public Node(boolean val, boolean isLeaf, Node topLeft, Node topRight, Node bottomLeft, Node bottomRight) {
        this.val = val;
        this.isLeaf = isLeaf;
        this.topLeft = topLeft;
        this.topRight = topRight;
        this.bottomLeft = bottomLeft;
        this.bottomRight = bottomRight;
    }
}
