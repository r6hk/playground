package dev.rennen.juc.basic;

/**
 * @author rennen.dev
 * @date 2024/9/25 15:22
 */
public class StringPlusTest{

    public static void main(String[] args) {
        String s1 = "123", s2 = "789";
        String s3 = s1 + "456" + s2;
        String s4 = s1 + "456" + s2;
        StringBuilder sb = new StringBuilder("123");
        System.out.println(sb.append(123).toString());
        System.out.println(s3 == s4);

        T3();
    }

    public static void T3() {
        //声明一个字符串
        String s = "";
        //开始时间
        Long start = System.currentTimeMillis();
        //循环遍历连接100000次字符串
        for (int i = 0; i < 1; i++) {
            s += "abc";
        }
        //结束时间
        Long end = System.currentTimeMillis();
        //耗时
        Long sj = end - start;
        //打印
        System.out.println("'+'操作符连接字符串用时:"+sj);
        //********************StringBuilder******************************
        //声明一个字符串
        StringBuilder str = new StringBuilder();
        //开始时间
        Long start1 = System.currentTimeMillis();
        //循环遍历连接100000次字符串
        for (int i = 0; i < 1; i++) {
            str.append("abc");
        }
        //结束时间
        Long end1 = System.currentTimeMillis();
        //耗时
        Long sj1 = end1 - start1;
        //打印
        System.out.println("StringBuilder连接字符串用时:"+sj1);
        //************************StringBuffer******************************
        //声明一个字符串
        StringBuffer sbf = new StringBuffer();
        //开始时间
        Long start2 = System.currentTimeMillis();
        //循环遍历连接100000次字符串
        for (int i = 0; i < 1; i++) {
            sbf.append("abc");
        }
        //结束时间
        Long end2 = System.currentTimeMillis();
        //耗时
        Long sj2 = end2 - start2;
        //打印
        System.out.println("StringBUffer连接字符串用时:"+sj2);
    }

}
