package dev.rennen.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author rennen.dev
 * @date 2024/8/28 19:01
 */
public class Test179 {

    public static void main(String[] args) {
        int[] nums = new int[]{3,30,34,5,9};
        System.out.println(largestNumber(nums));
    }

    public static String largestNumber(int[] nums) {
        StringBuilder res = new StringBuilder();
        List<Integer> numsList = new ArrayList<>();
        for (int num : nums) {
            numsList.add(num);
        }
        numsList.sort((t1, t2) -> {
            char[] c1 = t1.toString().toCharArray();
            char[] c2 = t2.toString().toCharArray();
            for (int i = 0; i < Math.min(c1.length, c2.length); i++) {
                int comp = -1 * Character.compare(c1[i], c2[i]);
                if (comp != 0) return comp;
            }
            if (c1.length == c2.length) return 0;
            else if (c1.length < c2.length) {
                return -1 * Character.compare(c1[c1.length - 1], c2[c1.length]);
            } else {
                return -1 * Character.compare(c1[c2.length], c2[c2.length - 1]);
            }
        });
        for (Integer i : numsList) {
            res.append(i);
        }
        return res.toString();
    }
}
