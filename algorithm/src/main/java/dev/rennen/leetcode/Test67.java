package dev.rennen.leetcode;

/**
 * @author rennen.dev
 * @date 2024/12/2 9:22
 */
public class Test67 {

    public static String addBinary(String a, String b) {
        // 将二进制字符串转换为整数
        int x = Integer.parseInt(a, 2);
        int y = Integer.parseInt(b, 2);

        // 进行二进制加法
        while (y != 0) {
            // 计算无进位的和
            int answer = x ^ y; // 0001
            // 计算进位部分
            int carry = (x & y) << 1;
            // 更新 x 和 y
            x = answer;
            y = carry;
        }

        // 将结果转换回二进制字符串并去掉"0b"前缀
        return Integer.toBinaryString(x);
    }

    public static void main(String[] args) {
        System.out.println(addBinary("1010", "1011"));
    }

}
