package dev.rennen.exam.mihoyo0803;

import java.util.*;

/**
 * @author rennen.dev
 * @date 2024/8/5 14:52
 */
public class Test3 {

    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        int T = sc.nextInt();
        for (int i = 0; i < T; i++) {
            solve();
        }
    }

    static void solve() {
        int n = sc.nextInt(), x = sc.nextInt();
        // 初始化图
        List<List<Integer>> graph = new ArrayList<>(n + 1);
        for (int i = 0; i <= n; i++) {
            graph.add(new ArrayList<>());
        }
        int[] indegree = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            int t1 = sc.nextInt(), t2 = sc.nextInt();
            graph.get(t1).add(t2);
            graph.get(t2).add(t1);
            indegree[t1]++;
            indegree[t2]++;
        }
        // 初始化集合
        LinkedList<Integer> deletable = new LinkedList<>();
        // 所有可被删除的节点总数
        int count = 0;
        for (int i = 1; i <= n; i++) {
            if (indegree[i] == 1) {
                if (i == x) {
                    // 如果最开始节点 x 的度数就是 1，那么肯定先手赢
                    System.out.println("Xiaoyo");
                    return;
                }
                deletable.add(i);
                count++;
            }
        }
        boolean solvable = false;
        while (!deletable.isEmpty()) {
            if (deletable.size() == 1 && deletable.get(0) == x) {
                solvable = true;
                break;
            }
            for (int i = deletable.size() - 1; i >= 0; i--) {
                int t = deletable.get(i);
                // 删除除了 x 之外其他所有能删除的节点
                if (t != x) {
                    deletable.remove(i);
                    for (int minus : graph.get(t)) {
                        indegree[minus]--;
                        if (indegree[minus] == 1) {
                            deletable.add(minus);
                            count++;
                        }
                    }
                    indegree[t]--;
                }
            }
        }
        if (!solvable) {
            System.out.println("Draw");
        } else if (count % 2 == 0) {
            System.out.println("Pyrmont");
        } else {
            System.out.println("Xiaoyo");
        }
    }
}
