package dev.rennen.exam.microfun0811;

import java.util.Optional;
import java.util.Scanner;
import java.util.TreeSet;

/**
 * @author rennen.dev
 * @date 2024/8/12 10:55
 */
public class Test2 {

    static TreeSet<Integer> numsSet = new TreeSet<>();
    static int n;
    static int[] nums;
    public static void main(String[] args) {
        try (Scanner sc = new Scanner(System.in)) {
            n = sc.nextInt();
            nums = new int[n];
            for (int i = 0; i < n; i++) {
                nums[i] = sc.nextInt();
            }
        }
        StringBuilder sb = new StringBuilder();
        for (int i = nums.length - 1; i >= 0; i--) {
            int t = nums[i];
            Integer higher = Optional.ofNullable(numsSet.higher(t)).orElse(-1);
            nums[i] = higher;
            numsSet.add(t);
        }
        for (int num : nums) {
            sb.append(num).append(" ");
        }
        System.out.println(sb);
    }
}
