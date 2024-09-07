package dev.rennen;

import java.util.HashSet;
import java.util.Set;

/**
 * @author rennen.dev
 * @date 2024/8/9 19:54
 */
//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        Set<Integer> set = new HashSet<>();
        set.add(1);
        set.add(1);
        System.out.println(set.size());
    }
}