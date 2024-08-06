package dev.rennen.exam.mihoyo0803;

import java.util.*;

/**
 * @author rennen.dev
 * @date 2024/8/5 10:23
 */
public class Test1 {

    public static void main(String[] args) {
        // 给定数字全为整数
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();

        List<Integer> numsList = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            numsList.add(sc.nextInt());
        }
        numsList.sort(Comparator.reverseOrder());
        long res = (long) numsList.get(0) * numsList.get(1);
        System.out.println(res);
    }
}
