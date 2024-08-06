package dev.rennen.exam.yonyou0801;

import java.util.*;

/**
 * @author rennen.dev
 */
public class Test4Answer {
    private static int n, m, r;
    private static int[] scores, heights, rests;
    private static int ans = 0;
    private static List<Integer> res1 = new ArrayList<>();
    private static List<Integer> res2 = new ArrayList<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        n = scanner.nextInt();
        m = scanner.nextInt();
        r = scanner.nextInt();

        scores = new int[n];
        heights = new int[n];
        rests = new int[n];

        for (int i = 0; i < n; i++) scores[i] = scanner.nextInt();
        for (int i = 0; i < n; i++) heights[i] = scanner.nextInt();
        for (int i = 0; i < n; i++) rests[i] = scanner.nextInt();

        scanner.close();

        List<Integer> path = new ArrayList<>();
        List<Integer> restPath = new ArrayList<>();
        Set<Integer> usd = new HashSet<>();

        backtrace(0, 0, usd, 0, path, restPath);

        System.out.println(ans);
        for (int i : res1) System.out.print((i + 1) + " ");
        System.out.println();
        for (int i : res2) System.out.print(i + " ");
        System.out.println();
    }

    private static void backtrace(int j, int cnt, Set<Integer> usd, int score, List<Integer> path, List<Integer> restPath) {
        if (ans < score) {
            ans = score;
            res1 = new ArrayList<>(path);
            res2 = new ArrayList<>(restPath);
        }
        if (ans == score) {
            // 用于选出字典序更优的答案
            if (compareLists(res1, path) > 0) {
                res1 = new ArrayList<>(path);
                res2 = new ArrayList<>(restPath);
            } else if (compareLists(res1, path) == 0 && compareLists(res2, restPath) > 0) {
                res2 = new ArrayList<>(restPath);
            }
        }

        // 注意，最优路径不一定要是 m 个景点
        if (cnt == m) return;

        boolean hasToRest = (j == r);

        for (int k = 0; k < n; k++) {
            if (usd.contains(k)) continue;
            if (!path.isEmpty() && heights[k] <= heights[path.get(path.size() - 1)]) continue;

            path.add(k);
            usd.add(k);

            if (!hasToRest) {
                // 还没必要休息
                if (rests[k] == 1) {
                    // 可以休息
                    restPath.add(1);
                    backtrace(0, cnt + 1, usd, score + scores[k], path, restPath);
                    restPath.remove(restPath.size() - 1);
                }
                // 不休息
                restPath.add(0);
                backtrace(j + 1, cnt + 1, usd, score + scores[k], path, restPath);
                restPath.remove(restPath.size() - 1);
            } else {
                // 一定要休息
                if (rests[k] == 1) {
                    restPath.add(1);
                    backtrace(0, cnt + 1, usd, score + scores[k], path, restPath);
                    restPath.remove(restPath.size() - 1);
                }
            }

            usd.remove(k);
            path.remove(path.size() - 1);
        }
    }

    private static int compareLists(List<Integer> list1, List<Integer> list2) {
        int size1 = list1.size();
        int size2 = list2.size();
        for (int i = 0; i < Math.min(size1, size2); i++) {
            if (!list1.get(i).equals(list2.get(i))) {
                return list1.get(i) - list2.get(i);
            }
        }
        return size1 - size2;
    }
}