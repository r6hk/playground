package dev.rennen.leetcode;

import java.util.*;

/**
 * <br/>
 * 2025/4/8
 *
 * @author rennen.dev
 */
public class Test1782 {
    public int[] countPairs(int n, int[][] edges, int[] queries) {
        List<Set<Integer>> point2Edge = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            point2Edge.add(new HashSet<>());
        }
        for (int i = 0; i < edges.length; i++) {
            int[] edge = edges[i];
            point2Edge.get(edge[0]).add(i);
            point2Edge.get(edge[1]).add(i);
        }
        TreeMap<Integer, Integer> pair = new TreeMap<>();
        for (int i = 1; i < n; i++) {
            for (int j = i + 1; j <= n; j++) {
                var t = new HashSet<>(point2Edge.get(i));
                t.addAll(point2Edge.get(j));
                pair.merge(t.size(), 1, Integer::sum);
            }
        }
        int[] res = new int[queries.length];
        int i = 0;
        for (int query : queries) {
            res[i++] = pair.tailMap(query + 1).values().stream().mapToInt(Integer::intValue).sum();
        }
        return res;
    }

    public static void main(String[] args) {
        Test1782 test1782 = new Test1782();
        int n = 4;
        int[][] edges = {{1, 2}, {2, 4}, {1, 3}, {2, 3}, {2, 1}};
        int[] queries = {2, 3};
        System.out.println(Arrays.toString(test1782.countPairs(n, edges, queries)));
    }
}