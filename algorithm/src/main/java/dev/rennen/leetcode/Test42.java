package dev.rennen.leetcode;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * <br/>
 * 2025/4/2
 *
 * @author rennen.dev
 */
public class Test42 {
    public int trap(int[] height) {
        int ans = 0;
        Deque<Integer> st = new ArrayDeque<>();
        for (int i = 0; i < height.length; i++) {
            while (!st.isEmpty() && height[i] >= height[st.peek()]) {
                int bottomH = height[st.pop()];
                if (st.isEmpty()) {
                    break;
                }
                int left = st.peek();
                int dh = Math.min(height[left], height[i]) - bottomH; // 面积的高
                ans += dh * (i - left - 1);
            }
            st.push(i);
        }
        return ans;
    }

    public static void main(String[] args) {
        var test42 = new Test42();
        System.out.println(test42.trap(new int[]{0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1}));
    }
}