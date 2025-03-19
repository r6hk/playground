package dev.rennen.leetcode;

/**
 * <br/>
 * 2025/3/19
 *
 * @author rennen.dev
 */
public class Test3258 {
    public int countKConstraintSubstrings(String s, int k) {
        int[] count = new int[2];
        int n = s.length();
        int res = 0;
        char[] c = s.toCharArray();
        for (int l = 0, r = 0; r < n; r++) {
            count[c[r] - '0']++;
            while (count[0] > k || count[1] > k) {
                count[c[l++] - '0']--;
            }
            res += (r - l + 1);
        }
        return res;
    }

    public static void main(String[] args) {
        System.out.println(new Test3258().countKConstraintSubstrings("10101", 1));
    }
}