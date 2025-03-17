package dev.rennen.leetcode;

import java.util.HashSet;
import java.util.Set;

/**
 * <br/> 2025/2/17 10:21
 *
 * @author rennen.dev
 */
public class Test1044 {
    static final int P1 = 233333;
    static final int P2 = 1313131;
    static int[] p1, hash1;
    static long[] p2, hash2;
    public static String longestDupSubstring(String s) {
        int n = s.length();
        p1 = new int[n + 1];
        hash1 = new int[n + 1];

        p2 = new long[n + 1];
        hash2 = new long[n + 1];
        p1[0] = 1;
        p2[0] = 1;
        for (int i = 1; i <= n; i++) {
            hash1[i] = hash1[i - 1] * P1 + s.charAt(i - 1);
            p1[i] = p1[i - 1] * P1;

            hash2[i] = hash2[i - 1] * P2 + s.charAt(i - 1);
            p2[i] = p2[i - 1] * P2;
        }
        // 二分
        String ans = "";
        int l = 1, r = n - 1;
        while (l <= r) {
            int m = l + (r - l) / 2;
            String t = check(s, m);
            if (!t.isEmpty()) {
                ans = t;
                l = m + 1;
            } else {
                r = m - 1;
            }
        }
        System.out.println("ans.length() = " + ans.length());
        System.out.println("r = " + r);
        return ans;
    }

    private static String check(String s, int l) {
        Set<Integer> contains1 = new HashSet<>();
        Set<Long> contains2 = new HashSet<>();
        int n = s.length();
        for (int i = 1; i + l - 1 <= n; i++) {
            int j = i + l - 1;
            int t1 = hash1[j] - hash1[i - 1] * p1[l];
            long t2 = hash2[j] - hash2[i - 1] * p2[l];
            if (contains1.contains(t1) && contains2.contains(t2)) {
                return s.substring(i - 1, j);
            }
            contains1.add(t1);
            contains2.add(t2);
        }
        return "";
    }

    public static void main(String[] args) {
        String s = "rtbbfywifhhthadpjixdeodqfgjfykvjwagudthnwfjdqyfskgczdzdsomkxfeizyfnmkirnklfevunbwvevymyoxcnddzhrqnengvjrptpgkusjfpcivknmpcptgbkosyujjcpqugnizpfqfxzrpyxmtbvpxqfbrrupmppkxyltyumcxtyefxjqlmpfymxvrbjmxvqtgnorweoujekkbpdzvhzvchdslcbmavwmjnlypoexhqoxfrnglcyhetrhpemrryhoanasaoyfxeznfeqkgqkzxcuphvngwryfouyugtrhxjfkegzgypucmpanrgyourwglemiclqkcubxbjzhkqzqextijwbfyizgylkyjxeuulkurebpovcxklywnwflnvmufbaauloekgtnabkvfvlsghtgqrkvopcablizoqdcxomhhyxtuwdebmjihmchdxtklvdecwvgogwwepqowfuwluklfiibyqaikphnfpfzhralzuuhsptonslvmkfojfdsumnwwacfwqxkotcqewulorinpzmhduhriisuajcpwjeanvyvpyefglpmfcicsglxtwadtrtaxnozxvchwagdyyinhqmhofuknauhwkinwzmrmermnnzndaxmqkgzotjgkxqhfqvgnvzymvcmdqpfiixrkmjpdbelzojbjeublnwcdsckbpwbbwlloxhecimaysjzwbppgmzywbouicyjdcsaffcxxkmwoamjzicswfzhccdlzewmzotyzprhreseqgmafkzkjqzwukrunckowdfajqhyrhhfhjfuzhvwgkwrmmpgribxqchowwvlbppnkofdfdennafqzawkycytghxehrjwdbrgroqfyoxynkketzkmglrsbqaxgfbiwhtuhzlpszsgbybawnguqhyadwteiikbahnhqdpvmobcsozloyyopxsnjlbgisytssbjbbuucqyvobnflnxtd";
        System.out.println(longestDupSubstring(s));
    }
}
