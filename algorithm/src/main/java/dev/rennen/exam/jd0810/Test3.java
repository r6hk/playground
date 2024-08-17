package dev.rennen.exam.jd0810;

import java.util.Scanner;

public class Test3 {

    public static long cal(long x) {
        long cnt = 0;
        while (x > 0) {
            if ((x & 1) == 1) {
                cnt++;
            }
            x >>= 1;
            cnt++;
        }
        return cnt - 1;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int T = scanner.nextInt();
        while (T-- > 0) {
            int n = scanner.nextInt();
            long[] b = new long[n + 1];

            for (int i = 1; i <= n; i++) {
                b[i] = cal(scanner.nextLong());
            }

            long ans = 0;
            for (int i = 2; i <= n; i++) {
                if (b[i] > b[i - 1]) {
                    ans += b[i] - b[i - 1];
                }
            }
            System.out.println(ans + b[1]);
        }

        scanner.close();
    }
}