package dev.rennen.leetcode;

import java.util.List;

/**
 * <br/>
 * 2025/3/19
 *
 * @author rennen.dev
 */
public class Test3306 {

    List<Character> vowels = List.of('a', 'e', 'i', 'o', 'u');

    public long countOfSubstrings(String word, int k) {
        char[] wordArray = word.toCharArray();
        return atLeast(wordArray, k) - atLeast(wordArray, k + 1);
    }

    private long atLeast(char[] word, int k) {
        int n = word.length;
        long res = 0;
        int[] vowelCount = new int[5];
        int vowelTypeCount = 0;
        int consonantCount = 0;
        for (int l = 0, r = 0; r < n; r++) {
            int i = vowels.indexOf(word[r]);
            if (i != -1) vowelCount[i]++;
            else consonantCount++;
            if (vowelCount[i] == 1) vowelTypeCount++;
            while (vowelTypeCount == 5 && consonantCount >= k) {
                int j = vowels.indexOf(word[l++]);
                if (j != -1) vowelCount[j]--;
                else consonantCount--;
                if (vowelCount[i] == 0) vowelTypeCount--;
            }
            res += l;
        }
        return res;
    }
}