package dev.rennen.exam.yonyou0801;

import java.util.*;

/**
 * @author rennen.dev
 * @date 2024/8/5 18:19
 */
public class Test3 {

    static int n;

    static int m;

    static int[] tasks;

    public static void main(String[] args) {
        try (Scanner sc = new Scanner(System.in)) {
            n = sc.nextInt();
            tasks = new int[n];
            for (int i = 0; i < n; i++) {
                tasks[i] = sc.nextInt();
            }
            m = sc.nextInt();
        }
        // dp[i] 表示从 0 到 i 的最小消耗时间
        int[] dp = new int[n];
        Arrays.fill(dp, -1);
        // 更新 dp 数组的同时还需记录路径
        List<Integer> path = new ArrayList<>();
        path.add(1);
        for (int i = 1; i < n; i++) {
            int minCost = Integer.MAX_VALUE;
            int choice = 0;
            boolean flag = false;
            for (int j = Math.max(0, i - m); j < i; j++) {
                if (tasks[j] == -1) {
                    continue;
                }
                if (dp[j] + tasks[j] < minCost) {
                    flag = true;
                    choice = j;
                    minCost = dp[j] + tasks[j];
                }
            }
            if (flag) {
                dp[i] = minCost;
                path.add(choice);
            }
        }
        if (dp[n - 1] == -1) {
            System.out.println("[]");
        } else {
            List<Integer> res = new ArrayList<>();
            int t = n - 1;
            res.add(t + 1);
            while (t != 0) {
                t = path.get(t);
                res.add(t + 1);
            }
            Collections.reverse(res);
            for (Integer out : res) {
                System.out.print(out + " ");
            }
        }

    }
}
