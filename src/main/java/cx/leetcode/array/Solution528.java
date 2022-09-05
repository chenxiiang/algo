package cx.leetcode.array;

import java.util.Random;

public class Solution528 {
    //前缀和+二分搜索
    private int[] preSum;

    private Random random = new Random();

    public Solution528(int[] w) {
        int n = w.length;
        preSum = new int[n + 1];
        preSum[0] = 0;
        for (int i = 1; i <= n; i++) {
            preSum[i] = preSum[i - 1] + w[i - 1];
        }
    }

    public int pickIndex() {
        int n = preSum.length;
        int target = random.nextInt(preSum[n - 1]) + 1;
        return left(preSum, target) - 1;
    }

    private int left(int[] nums, int target) {
        if (nums.length == 0) {
            return -1;
        }
        int left = 0, right = nums.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return left;
    }
}
