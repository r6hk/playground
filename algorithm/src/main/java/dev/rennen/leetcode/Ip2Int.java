package dev.rennen.leetcode;

/**
 * @author rennen.dev
 * @date 2024/9/24 14:57
 */
public class Ip2Int {

    public static void main(String[] args) {
        System.out.println(ip2Int("111.222.123.123"));
    }

    static int ip2Int(String ipv4) {
        String[] split = ipv4.split("\\.");
        int res = 0;
        res += (Integer.parseInt(split[0]) << 24);
        res += (Integer.parseInt(split[1]) << 16);
        res += (Integer.parseInt(split[2]) << 8);
        res += Integer.parseInt(split[3]);
        return res;
    }
}
