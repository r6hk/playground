package dev.rennen.leetcode;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

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

    public static int ladderLength(String beginWord, String endWord, List<String> wordList) {
        LinkedList<Pair> queue = new LinkedList<>();
        Set<String> visited = new HashSet<>();
        queue.add(new Pair(beginWord, 1));
        while (!queue.isEmpty()) {
            Pair t = queue.removeFirst();
            if (t.word.equals(endWord)) return t.layer;
            for (String s : wordList) {
                if (!visited.contains(s) && compare(t.word, s)) {
                    queue.add(new Pair(s, t.layer + 1));
                    visited.add(s);
                }
            }
        }
        return 0;
    }

    private static boolean compare(String a, String b) {
        char[] ac = a.toCharArray();
        char[] bc = b.toCharArray();
        int count = 0;
        for (int i = 0; i < ac.length; i++) {
            if (ac[i] != bc[i]) count++;
            if (count > 1) return false;
        }
        return count == 1;
    }

    private static class Pair {
        String word;
        int layer;

        public Pair(String word, int layer) {
            this.word = word;
            this.layer = layer;
        }
    }
}
