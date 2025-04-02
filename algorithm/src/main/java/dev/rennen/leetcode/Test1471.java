package dev.rennen.leetcode;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <br/>
 * 2025/3/20
 *
 * @author rennen.dev
 */
public class Test1471 {

    public int[] getStrongest(int[] arr, int k) {
        List<Integer> t = List.of(1, 2, 3);
        List<Integer> nums = Arrays.stream(arr).boxed().collect(Collectors.toList());
        int n = arr.length;
        nums.sort(Comparator.naturalOrder());
        int m = nums.get((n - 1) / 2);
        nums.sort(Comparator
                .comparingInt((Integer x) -> Math.abs(x - m)) // 先按距离 3 的绝对值排序
                .reversed() // 变成降序
                .thenComparing(Comparator.reverseOrder())); // 如果距离相同，则按数值降序
        return nums.stream().limit(k).mapToInt(Integer::intValue).toArray();
    }

    public static void main(String[] args) {
        int[] arr = new int[]{6,7,11,7,6,8};
        Test1471 test1471 = new Test1471();
        System.out.println(Arrays.toString(test1471.getStrongest(arr, 5)));
    }
}