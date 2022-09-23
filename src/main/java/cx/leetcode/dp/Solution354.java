package cx.leetcode.dp;

import java.util.Arrays;

public class Solution354 {
    public int maxEnvelopes(int[][] envelopes) {
        int n = envelopes.length;
        //这道题的精华是这个排序，把宽度按升序排序，宽相同的按照高度降序排列，这样当高度大于当前值的数组宽度也一定大于当前的宽度
        Arrays.sort(envelopes, (a, b) -> {
            if (a[0] == b[0]) {
                return b[1] - a[1];
            }
            return a[0] - b[0];
        });
        int[] height = new int[n];
        for (int i = 0; i < height.length; i++) {
            height[i] = envelopes[i][1];
        }
        int ans = 0;
        int[] top = new int[n];
        for (int i = 0; i < n; i++) {
            int h = height[i];
            int left = 0, right = ans;
            while (left < right) {
                int mid = (left + right) / 2;
                if (top[mid] >= h) {
                    right = mid;
                } else {
                    left = mid + 1;
                }
            }
            if (left == ans) {
                ans++;
            }
            top[left] = h;
        }
        return ans;
    }
}
