package dev.rennen.exam.huawei0911;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static int cal(List<Integer> a, int k, int n) {
        int l = 0, cnt = 0, res = 0;
        for (int r = 0; r < a.size(); r++) {
            if (r != 0) {
                cnt += a.get(r) - a.get(r - 1) - 1;
            }
            while (cnt > k) {
                cnt -= a.get(l + 1) - a.get(l) - 1;
                l += 1;
            }
            res = Math.max(res, a.get(r) - a.get(l) + 1 + k - cnt);
        }
        return Math.min(n, res);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int m = scanner.nextInt();
        int k = scanner.nextInt();

        List<List<Integer>> idx = new ArrayList<>();
        for (int i = 0; i <= m; i++) {
            idx.add(new ArrayList<>());
        }

        List<Integer> v = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            int value = scanner.nextInt();
            v.add(value);
            idx.get(value).add(i);
        }

        int ans = k;
        for (List<Integer> id : idx) {
            ans = Math.max(ans, cal(id, k, n));
        }

        System.out.println(ans);

        scanner.close();
    }
}