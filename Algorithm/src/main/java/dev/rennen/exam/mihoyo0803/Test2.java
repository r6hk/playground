package dev.rennen.exam.mihoyo0803;

import java.util.*;

/**
 * @author rennen.dev
 * @date 2024/8/5 10:42
 */
public class Test2 {

    static Set<Integer> bag = new HashSet<>();
    static int weight = 0;
    static int value = 0;
    static int res = 0;
    static int n;
    static int m;
    static int k;
    static Map<Integer, Set<Integer>> mutual;
    static List<int[]> table;

    public static void main(String[] args) {
        // init
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        m = sc.nextInt();
        k = sc.nextInt();
        // 存储所有节点的互斥关系
        mutual = new HashMap<>();
        // 存储所有商品的价格和体积
        table = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            // item[0]: 体积， item[1]: 价格
            table.add(new int[]{sc.nextInt(), sc.nextInt()});
        }
        for (int i = 0; i < k; i++) {
            int t1 = sc.nextInt(), t2 = sc.nextInt();
            addMutual(t1, t2);
            addMutual(t2, t1);
        }

        // 对于每个物品，取或不取，暴力解法可以使用回溯法
        backtracking(1, n);
        System.out.println(res);

    }

    static void addMutual(int t1, int t2) {
        if (mutual.containsKey(t1)) {
            mutual.get(t1).add(t2);
        } else {
            HashSet<Integer> value = new HashSet<>();
            value.add(t2);
            mutual.put(t1, value);
        }
    }

    static void backtracking(int i, int n) {
        if (i > n) {
            return;
        }
        int[] item = table.get(i - 1);
        // 判断是否存在互斥
        boolean isMutual = false;
        for (int t : bag) {
            if (mutual.get(t).contains(i)) {
                // 代表当前要装入的物品，和当前已经装入背包的物品存在互斥
                isMutual = true;
                break;
            }
        }
        // 当前 item 可不装
        backtracking(i + 1, n);
        if (m - weight >= item[0] && !isMutual) {
            // 如果空间有盈余且不互斥，那么当前 item 可装
            weight += item[0];
            value += item[1];
            res = Math.max(res, value);
            bag.add(i);
            backtracking(i + 1, n);
            // 注意消除此次回溯的影响
            weight -= item[0];
            value -= item[1];
            bag.remove(i);
        }
    }
}
