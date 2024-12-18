package dev.rennen.leetcode;

import java.util.*;

/**
 * @author rennen.dev
 * @date 2024/12/5 9:56
 */
public class Test127 {

    public static void main(String[] args) {
        String beginWord = "hit";
        String endWord = "cog";
        List<String> wordList = new LinkedList<>();
        wordList.add("hot");
        wordList.add("dot");
        wordList.add("dog");
        wordList.add("lot");
        wordList.add("log");
        wordList.add("cog");
        System.out.println(ladderLength(beginWord, endWord, wordList));
    }

    static Map<String, Integer> index = new HashMap<>();
    static int count = 0;
    static List<List<Integer>> graph = new ArrayList<>();

    public static int ladderLength(String beginWord, String endWord, List<String> wordList) {
        addEdges(beginWord);
        for (String t : wordList) {
            addEdges(t);
        }
        if (!index.containsKey(endWord)) {
            return 0;
        }
        int begin = index.get(beginWord);
        int end = index.get(endWord);
        LinkedList<Integer> queue = new LinkedList<>();
        queue.addLast(begin);
        int[] dis = new int[count];
        Arrays.fill(dis, Integer.MAX_VALUE);
        dis[0] = 0;
        while (!queue.isEmpty()) {
            int t = queue.removeFirst();
            if (t == end) return dis[t] / 2 + 1;
            List<Integer> tmp = graph.get(t);
            for (int i : tmp) {
                if (dis[i] != Integer.MAX_VALUE) continue;
                queue.add(i);
                dis[i] = dis[t] + 1;
            }
        }
        return 0;
    }

    private static void addEdges(String word) {
        addWord(word);
        int m = index.get(word);
        char[] c = word.toCharArray();
        for (int i = 0; i < c.length; i++) {
            char t = c[i];
            c[i] = '*';
            String newWord = new String(c);
            addWord(newWord);
            int n = index.get(newWord);
            graph.get(m).add(n);
            graph.get(n).add(m);
            c[i] = t;
        }
    }

    private static void addWord(String word) {
        if (!index.containsKey(word)) {
            index.put(word, count++);
            graph.add(new ArrayList<>());
        }
    }
}
