package dev.rennen.leetcode;

import java.util.LinkedList;

public class Test150 {
    public static int evalRPN(String[] tokens) {
        LinkedList<Integer> nums = new LinkedList<>();
        for (String token : tokens) {
            if ("+-*/".contains(token)) {
                int num2 = nums.removeLast();
                int num1 = nums.removeLast();
                switch (token) {
                    case "+":
                        nums.addLast(num1 + num2);
                        break;
                    case "-":
                        nums.addLast(num1 - num2);
                        break;
                    case "*":
                        nums.addLast(num1 * num2);
                        break;
                    case "/":
                        nums.addLast(num1 / num2);
                        break;
                    default:
                }
            } else {
                nums.addLast(Integer.valueOf(token));
            }
        }
        return nums.getLast();
    }

    public static void main(String[] args) {
        System.out.println(evalRPN(new String[]{"4", "13", "5", "/", "+"}));
    }
}