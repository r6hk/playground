package dev.rennen.leetcode;

/**
 * <br/>
 * 2025/4/2
 *
 * @author rennen.dev
 */
public class Test1616 {
    public boolean checkPalindromeFormation(String a, String b) {
        // 先判断 a, b 本身是不是回文串
        return isPalindrome(a) || isPalindrome(b) || check(a, b) || check(b, a);
    }

    private boolean check(String a, String b) {
        int n = a.length();
        int l = 0, r = n - 1;
        char[] ca = a.toCharArray();
        char[] cb = b.toCharArray();
        boolean change = false;
        while (l < r) {
            if (ca[l] == cb[r]) {
                l++;
                r--;
            } else {
                return isPalindrome(a.substring(l, r + 1)) || isPalindrome(b.substring(l, r + 1));
            }
        }
        return true;
    }

    private boolean isPalindrome(String a) {
        return a.contentEquals(new StringBuilder(a).reverse());
    }


}

