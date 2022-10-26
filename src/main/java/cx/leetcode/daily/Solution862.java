package cx.leetcode.daily;

import java.util.ArrayDeque;
import java.util.Deque;

public class Solution862 {
    public int shortestSubarray(int[] nums, int k) {
        int n = nums.length, ans = n + 1;
        long[] preSum = new long[n + 1];
        for (int i = 0; i < n; i++) {
            preSum[i + 1] = preSum[i] + nums[i];
        }
        Deque<Integer> q = new ArrayDeque<>();
        for (int i = 0; i <= n; i++) {
            long curS = preSum[i];
            while (!q.isEmpty() && curS - preSum[q.peek()] >= k) {
                ans = Math.min(ans, i - q.pollFirst());
            }
            while (!q.isEmpty() && preSum[q.peekLast()] >= curS) {
                q.pollLast();
            }
            q.addLast(i);
        }
        return ans > n ? -1 : ans;
    }
}
