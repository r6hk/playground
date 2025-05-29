package dev.rennen.leetcode;

/**
 * <br/>
 * 2025/5/21
 *
 * @author rennen.dev
 */
public class Test3356 {

    public int minZeroArray(int[] nums, int[][] queries) {
        int n = nums.length;
        int m = queries.length;

        int l = 0, r = m;
        while (l <= r) {
            int mid = l + (r - l) / 2;
            if (checkNums(get(mid, queries, n), nums)) {
                r = mid - 1;
            } else {
                l = mid + 1;
            }
        }
        if (l > m) return -1;
        return l;
    }

    private boolean checkNums(int[] nums1, int[] nums2) {
        for (int i = 0; i < nums2.length; i++) {
            if (nums1[i] < nums2[i]) return false;
        }
        return true;
    }

    private int[] get(int t, int[][] queries, int n) {
        var sum = new int[n + 1];
        if (t == 0) return sum;
        for (int i = 0; i < t; i++) {
            int[] query = queries[i];
            sum[query[0]] += query[2];
            sum[query[1] + 1] -= query[2];
        }
        for (int i = 1; i <= n; i++) {
            sum[i] += sum[i - 1];
        }
        return sum;
    }

    public static void main(String[] args) {
        Test3356 test = new Test3356();

        // 测试用例
        int[] nums = {2, 0, 2};
        int[][] queries = {{0, 2, 1}, {0, 2, 1}, {1, 1, 3}};

        int result = test.minZeroArray(nums, queries);
        System.out.println("最小操作次数: " + result);

        // 验证结果正确性
        System.out.println("预期结果应为查询次数小于等于: " + result + " 时，使得数组变为全零");

        // 打印经过不同查询次数处理后的数组
        for (int i = 1; i <= queries.length; i++) {
            int[] processedArray = test.get(i, queries, nums.length);
            System.out.print("执行 " + i + " 次查询后的数组: [");
            for (int j = 0; j < processedArray.length; j++) {
                System.out.print(processedArray[j] + (j < processedArray.length - 1 ? ", " : ""));
            }
            System.out.println("]");
        }
    }
}
