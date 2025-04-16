package dev.rennen.leetcode;

import java.util.Arrays;

/**
 * <br/>
 * 2025/4/2
 *
 * @author rennen.dev
 */
public class Test1498 {

    int MOD = (int) 1e9 + 7;

    public int numSubseq(int[] nums, int target) {
        Arrays.sort(nums);
        long res = 0;
        for (int i = nums.length - 1; i >= 0; i--) {
            int a = nums[i], b = target - nums[i];
            if (a >= target) continue;
            int pos = binarySearch(nums, b);
            if (pos < 0) continue;
            if (pos >= i) res += powOfTwo(i);
            else res += (powOfTwo(i) - powOfTwo(i - pos - 1)) % MOD;
        }
        return (int) (res % MOD);
    }

    private int powOfTwo(int n) {
        long res = 1;
        long base = 2;
        while (n > 0) {
            if ((n & 1) == 1) {
                res = (res * base) % MOD;
            }
            base = (base * base) % MOD;
            n >>= 1;
        }
        return (int) res % MOD;
    }

    private int binarySearch(int[] nums, int num) {
        int left = 0, right = nums.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] <= num) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return right; // 返回最后一个小于等于num的索引
    }

    public static void main(String[] args) {
        Test1498 test1498 = new Test1498();
        int[] nums = {2,3,3,4,6,7};
        int target = 12;
        System.out.println(test1498.numSubseq(nums, target));
    }
}