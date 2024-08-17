package dev.rennen.exam.microfun0811;

import java.util.*;

/**
 * @author rennen.dev
 * @date 2024/8/12 15:48
 */
public class Test3 {

    static List<Work> works = new ArrayList<>();
    static int n;

    public static void main(String[] args) {
        try (Scanner sc = new Scanner(System.in)) {
            n = sc.nextInt();
            for (int i = 0; i < n; i++) {
                Work work = new Work();
                work.time = sc.nextInt();
                work.money = sc.nextInt();
                works.add(work);
            }
        }
        works.sort(Comparator.comparingInt(c -> c.time));
        PriorityQueue<Integer> queue = new PriorityQueue<>();
        int res = 0;
        for (Work work : works) {
            if (work.time > queue.size()) {
                queue.add(work.money);
                res += work.money;
            } else if (!queue.isEmpty() && work.money > queue.peek()) {
                res -= queue.poll();
                queue.add(work.money);
                res += work.money;
            }
        }
        System.out.println(res);

    }

    static class Work{
        int time;
        int money;
    }
}
