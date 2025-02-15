package dev.rennen.leetcode;

/**
 * @author rennen.dev
 * @since 2025/1/3 10:44
 */
public class Test676 {
    public static void main(String[] args) {
        MagicDictionary magicDictionary = new MagicDictionary();
        magicDictionary.buildDict(new String[]{"hello", "leetcode"});
        System.out.println(magicDictionary.search("leetcodd"));
    }
}

class MagicDictionary {

    T trie = new T();


    public void buildDict(String[] dictionary) {
        for (String t : dictionary) {
            trie.insert(t);
        }
    }

    public boolean search(String searchWord) {
        char[] wordArray = searchWord.toCharArray();
        return dfs(trie, wordArray, 0, 0);
    }

    private boolean dfs(T trie, char[] wordArray, int layer, int faultCount) {

        boolean res = false;
        for (int i = 0; i < 26; i++) {
            if (trie.table[i] != null) {
                if (wordArray[layer] - 'a' == i) {
                    if (layer >= wordArray.length - 1) {
                        return faultCount == 1 && trie.table[i].endWord != null;
                    }
                    res = dfs(trie.table[i], wordArray, layer + 1, faultCount);
                } else if (faultCount < 1){
                    if (layer >= wordArray.length - 1) {
                        return trie.table[i].endWord != null;
                    }
                    res = dfs(trie.table[i], wordArray, layer + 1, faultCount + 1);
                }
            }
            if (res) break;
        }
        return res;
    }
}

class T {
    T[] table = new T[26];
    String endWord;

    public void insert(String s) {
        T t = this;
        for (char c : s.toCharArray()) {
            int i = c - 'a';
            if (t.table[i] == null) {
                t.table[i] = new T();
            }
            t = t.table[i];
        }
        t.endWord = s;
    }

    public boolean searchPrefix(String word) {
        T t = search(word);
        return t != null;
    }

    public boolean searchWord(String word) {
        T t = search(word);
        return t != null && t.endWord != null;
    }

    private T search(String word) {
        T t = this;
        for (char c : word.toCharArray()) {
            int i = c - 'a';
            if (t.table[i] == null) return null;
            t = t.table[i];
        }
        return t;
    }
}

/**
 * Your MagicDictionary object will be instantiated and called as such:
 * MagicDictionary obj = new MagicDictionary();
 * obj.buildDict(dictionary);
 * boolean param_2 = obj.search(searchWord);
 */