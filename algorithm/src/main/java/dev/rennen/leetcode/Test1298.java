package dev.rennen.leetcode;

import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;

/**
 * <br/>
 * 2025/6/3
 *
 * @author rennen.dev
 */
public class Test1298 {

    public int maxCandies(int[] status, int[] candies, int[][] keys, int[][] containedBoxes, int[] initialBoxes) {
        var lockedBox = new HashSet<Integer>();
        var keySet = new HashSet<Integer>();
        int res = 0;
        var box = new LinkedList<Integer>();
        addBox(status, initialBoxes, lockedBox, box);
        while (!box.isEmpty()) {
            var b = box.removeFirst();
            res += candies[b];
            addBox(status, containedBoxes[b], lockedBox, box);
            keySet.addAll(Arrays.stream(keys[b]).boxed().toList());
            var tmpSet = new HashSet<>(lockedBox);
            tmpSet.retainAll(keySet);
            box.addAll(tmpSet);
            keySet.removeAll(tmpSet);
            lockedBox.removeAll(tmpSet);
        }
        return res;
    }

    private void addBox(int[] status, int[] boxes, HashSet<Integer> lockedBox, LinkedList<Integer> box) {
        Arrays.stream(boxes)
                .forEach(t -> {
                    if (status[t] == 0) {
                        lockedBox.add(t);
                    } else {
                        box.add(t);
                    }
                });
    }

    public static void main(String[] args) {
        int[] status = {1, 0, 1, 0};
        int[] candies = {7, 5, 4, 100};
        int[][] keys = {{}, {}, {1}, {}};
        int[][] containedBoxes = {{1, 2}, {3}, {}, {}};
        int[] initialBoxes = {0};

        Test1298 solution = new Test1298();
        int result = solution.maxCandies(status, candies, keys, containedBoxes, initialBoxes);
        System.out.println("最大能收集的糖果数：" + result);
        // 预期结果应该是 16 (盒子0的7颗糖果 + 盒子1的5颗糖果 + 盒子2的4颗糖果)
        assert result == 16;
    }
}
