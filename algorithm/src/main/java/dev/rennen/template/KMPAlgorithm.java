package dev.rennen.template;

public class KMPAlgorithm {
    
    // KMP 字符串匹配算法
    public static int kmpSearch(String string, String patt) {
        int[] next = buildNext(patt); // 计算 next 数组

        int i = 0; // 主串指针
        int j = 0; // 子串指针

        while (i < string.length()) {
            if (string.charAt(i) == patt.charAt(j)) {
                // 字符匹配，指针后移
                i++;
                j++;
            } else if (j > 0) {
                // 失配，根据 next 数组回溯
                j = next[j - 1];
            } else {
                // 子串第一个字符就失配
                i++;
            }

            // 完全匹配
            if (j == patt.length()) {
                return i - j; // 返回匹配起始位置
            }
        }

        return -1; // 未匹配返回 -1
    }
    
    // 计算 next 数组
    private static int[] buildNext(String patt) {
        int[] next = new int[patt.length()];
        next[0] = 0; // 初始化第一个元素
        int prefixLen = 0; // 共同前后缀长度
        int i = 1;
        
        while (i < patt.length()) {
            if (patt.charAt(prefixLen) == patt.charAt(i)) {
                prefixLen++;
                next[i] = prefixLen;
                i++;
            } else if (prefixLen == 0) {
                next[i] = 0;
                i++;
            } else {
                prefixLen = next[prefixLen - 1]; // 回溯
            }
        }
        
        return next;
    }

    // 测试代码
    public static void main(String[] args) {
        String s1 = "aa";
        String pattern = "aba";
        int index = kmpSearch(new StringBuilder(s1).append(s1).toString(), pattern);
        System.out.println("匹配位置: " + index);
    }
}
