package dev.rennen.leetcode;

import java.io.IOException;
import java.util.*;

/**
 * @author rennen.dev
 * @date 2024/12/6 8:37
 */
public class Test187 {

    private static final Map<Character, Integer> index = new HashMap<>();

    public static List<String> findRepeatedDnaSequences(String s) {
        index.put('A', 0);
        index.put('C', 1);
        index.put('G', 2);
        index.put('T', 3);
        Set<Integer> visited = new HashSet<>();
        Set<Integer> res = new HashSet<>();
        char[] arr = s.toCharArray();
        if (arr.length <= 10) return new ArrayList<>();
        int t = 0;
        for (int i = 0; i < 10; i++) {
            t <<= 2;
            t |= index.get(arr[i]);
        }
        visited.add(t);
        int mask = (1 << 20) - 1;
        for (int i = 10; i < arr.length; i++) {
            t <<= 2;
            t |= index.get(arr[i]);
            t &= mask;
            if (visited.contains(t)) res.add(t);
            visited.add(t);
        }
        List<String> resList = new ArrayList<>();
        for (Integer integer : res) {
            resList.add(int2Str(integer));
        }
        return resList;
    }

    private static String int2Str(int t) {
        StringBuilder sb = new StringBuilder();
        int mask = (1 << 2) - 1;
        for (int i = 0; i < 10; i++) {
            int t2 = t & mask;
            switch (t2) {
                case 0:
                    sb.append('A');
                    break;
                case 1:
                    sb.append('C');
                    break;
                case 2:
                    sb.append('G');
                    break;
                case 3:
                    sb.append('T');
                    break;
                default:
                    break;
            }
            t >>>= 2;
        }
        return sb.reverse().toString();
    }

    public static void main(String[] args) throws IOException {
        List<String> res = findRepeatedDnaSequences("AAAAAAAAAAAAA");
        System.in.read();
    }

}
