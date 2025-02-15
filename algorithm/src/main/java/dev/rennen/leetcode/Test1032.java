package dev.rennen.leetcode;

import java.util.LinkedList;

/**
 * @author rennen.dev
 * @since 2025/1/5 10:43
 */
public class Test1032 {
    public static void main(String[] args) {
        StreamChecker streamChecker = new StreamChecker(new String[]{"cd", "f", "kl"});
        System.out.println(streamChecker.query('a'));
        System.out.println(streamChecker.query('b'));
        System.out.println(streamChecker.query('c'));
        System.out.println(streamChecker.query('d'));
        System.out.println(streamChecker.query('e'));
        System.out.println(streamChecker.query('f'));
        System.out.println(streamChecker.query('g'));
        System.out.println(streamChecker.query('h'));
        System.out.println(streamChecker.query('i'));
        System.out.println(streamChecker.query('j'));
        System.out.println(streamChecker.query('k'));
        System.out.println(streamChecker.query('l'));
    }
}

class StreamChecker {

    LinkedList<Character> stack = new LinkedList<>();
    Trie trie = new Trie();

    public StreamChecker(String[] words) {
        for (String word : words) {
            trie.insert(new StringBuilder(word).reverse().toString());
        }
    }

    public boolean query(char letter) {
        stack.addFirst(letter);
        Trie t = trie;
        for (char c : stack) {
            int i = c - 'a';
            if (t.table[i] != null) {
                t = t.table[i];
                if (t.isEnd) return true;
            } else {
                return false;
            }
        }
        return false;
    }
}

class Trie {
    Trie[] table = new Trie[26];
    boolean isEnd = false;

    public void insert(String word) {
        Trie t = this;
        for (char c : word.toCharArray()) {
            int i = c - 'a';
            if (t.table[i] == null) {
                t.table[i] = new Trie();
            }
            t = t.table[i];
        }
        t.isEnd = true;
    }
}