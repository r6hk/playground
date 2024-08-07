package dev.rennen.exam.flytech0727;

import java.util.*;

/**
 * @author rennen.dev
 * @date 2024/8/6 14:52
 */
public class Test1 {

    static int n, m;
    static int[] money;
    static List<House> houses = new ArrayList<>();

    public static void main(String[] args) {
        try (Scanner sc = new Scanner(System.in)) {
            n = sc.nextInt();
            m = sc.nextInt();
            money = new int[n];
            for (int i = 0; i < money.length; i++) {
                money[i] = sc.nextInt();
            }
            Arrays.sort(money);
            for (int i = 0; i < m; i++) {
                House house = new House();
                house.setComfortLevel(sc.nextInt());
                house.setPrice(sc.nextInt());
                houses.add(house);
            }
            houses.sort(Comparator.naturalOrder());
        }
        int res = 0;
        Set<Integer> bought = new HashSet<>();
        int[] priceList = houses.stream().mapToInt(House::getPrice).toArray();
        for (int i : money) {
            // 贪心策略：钱从小到大，找不超过 i 的最舒适的、未被购买的房子，找不到就算了
            int target = binarySearch(priceList, i);
            while (target >= 0) {
                if (bought.contains(target)) {
                    target--;
                } else {
                    bought.add(target);
                    res += houses.get(target).getComfortLevel();
                }
            }
        }
        System.out.println(res);
    }

    private static int binarySearch(int[] list, int target) {
        int l = 0, r = list.length - 1;
        while (l <= r) {
            int m = l + (r - l) / 2;
            if (list[m] <= target) {
                l = m + 1;
            } else {
                r = m - 1;
            }
        }
        return r;
    }

    public static class House implements Comparable<House> {

        int price;
        int comfortLevel;

        @Override
        public int compareTo(House o) {
            return Comparator.comparingInt(House::getPrice)
                    .thenComparingInt(house -> house.getComfortLevel() * -1)
                    .compare(this, o);
        }

        public int getPrice() {
            return price;
        }

        public void setPrice(int price) {
            this.price = price;
        }

        public int getComfortLevel() {
            return comfortLevel;
        }

        public void setComfortLevel(int comfortLevel) {
            this.comfortLevel = comfortLevel;
        }
    }
}
