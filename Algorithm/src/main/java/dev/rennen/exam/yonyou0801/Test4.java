package dev.rennen.exam.yonyou0801;

import java.util.*;

/**
 * @author rennen.dev
 * @date 2024/8/5 18:32
 */
public class Test4  {

    static int n, m, r;
    static int[] scores, heights, rest;
    static List<Site> sites = new ArrayList<>();

    /**
     * 以下变量用于回溯
     */
    static List<Site> currentPath = new ArrayList<>();
    static int currentScore = 0;
    static List<Integer> currentBreak = new ArrayList<>();

    /**
     * 以下变量用于存放结果
     */
    static List<Site> path = new ArrayList<>();
    static int maxScore = 0;
    static List<Integer> breaks = new ArrayList<>();

    public static void main(String[] args) {
        inputData();
        buildSites();
        for (int i = 0; i < n; i++) {
            backtracking(0, i, 0);
        }
        System.out.println(maxScore);
        path.stream().map(Site::getNumber).forEach(t -> System.out.print(t + " "));
        System.out.println();
        breaks.forEach(t -> System.out.print(t + " "));
    }

    /**
     * 回溯
     * @param times 当前是第几次
     * @param cur 当前位置
     * @param steps 连续行走步数
     */
    private static void backtracking(int times, int cur, int steps) {
        if (times == m && currentScore > maxScore) {
            // 回溯成功，得到一组答案，并与当前的最优答案比较，只有严格大于才更新，保证字典序最小
            maxScore = currentScore;
            path = new ArrayList<>(currentPath);
            breaks = new ArrayList<>(currentBreak);
            return;
        }
        if (cur + (m - times) > n || times >= m) {
            // 回溯失败
            return;
        }
        Site currentSite = sites.get(cur);
        // 景点海拔严格大于
        boolean bigger = currentPath.isEmpty() || (currentSite.height > currentPath.get(currentPath.size() - 1).height);
        // 选择当前景点
        if (steps <= r && bigger) {
            // 是否需要休息
            boolean needRest = steps == r;
            if (!needRest) {
                // 可休息可不休息，那么优先不休息
                currentPath.add(currentSite);
                currentScore += currentSite.getScore();
                currentBreak.add(0);
                backtracking(times + 1, cur + 1, steps + 1);
                if (currentSite.getRest() == 1) {
                    currentBreak.remove(currentBreak.size() - 1);
                    currentBreak.add(1);
                    backtracking(times + 1, cur + 1, 0);
                }
                currentBreak.remove(currentBreak.size() - 1);
                currentPath.remove(currentPath.size() - 1);
                currentScore -= currentSite.getScore();
            } else if (currentSite.getRest() == 1) {
                // 一定要休息
                currentPath.add(currentSite);
                currentScore += currentSite.getScore();
                currentBreak.add(1);
                backtracking(times + 1, cur + 1, 0);
                currentBreak.remove(currentBreak.size() - 1);
                currentPath.remove(currentPath.size() - 1);
                currentScore -= currentSite.getScore();
            }
            // 其余情况，代表不能选当前景点
        }
        // 不选当前景点
        backtracking(times, cur + 1, steps);
    }

    private static void inputData() {
        // 由于 n 较小，所以可以使用回溯法
        try (Scanner sc = new Scanner(System.in)) {
            n = sc.nextInt();
            m = sc.nextInt();
            r = sc.nextInt();
            scores = fillArray(n, sc);
            heights = fillArray(n, sc);
            rest = fillArray(n, sc);
        }
    }

    private static void buildSites() {
        for (int i = 0; i < n; i++) {
            Site t = new Site();
            t.setHeight(heights[i]);
            t.setNumber(i + 1);
            t.setRest(rest[i]);
            t.setScore(scores[i]);
            sites.add(t);
        }
        // 排序保证景点的海拔严格大于之前参观过的所有景点，且满足字典序
        sites.sort(Comparator.naturalOrder());
    }

    private static int[] fillArray(int n, Scanner sc) {
        int[] res = new int[n];
        for (int i = 0; i < n; i++) {
            res[i] = sc.nextInt();
        }
        return res;
    }

    static class Site implements Comparable<Site> {

        private int number;
        private int score;
        private int height;
        private int rest;


        @Override
        public int compareTo(Site o) {
            return Comparator.comparingInt((Site s) -> s.height)
                    .thenComparingInt(s -> s.score)
                    .thenComparingInt(s -> s.number)
                    .thenComparingInt(s -> s.rest == 0 ? 1 : -1)
                    .compare(this, o);
        }

        public int getNumber() {
            return number;
        }

        public void setNumber(int number) {
            this.number = number;
        }

        public int getScore() {
            return score;
        }

        public void setScore(int score) {
            this.score = score;
        }

        public int getHeight() {
            return height;
        }

        public void setHeight(int height) {
            this.height = height;
        }

        public int getRest() {
            return rest;
        }

        public void setRest(int rest) {
            this.rest = rest;
        }
    }
}


