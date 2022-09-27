package cx.leetcode.dp;

import java.util.Arrays;

public class Solution494 {
    public int findTargetSumWays(int[] nums, int target) {
//        sum是数组的和，neg是取负的数字的和，通过运算可以得知neg=(sum-target)/2
//        问题转化为在数组中选取若干元素使得和等于neg
        int sum = Arrays.stream(nums).sum();
        int diff = sum - target;
        if (diff < 0 || diff % 2 != 0) {
            return 0;
        }
        int n = nums.length, neg = diff / 2;
        int[][] dp = new int[n + 1][neg + 1];
        //            当没有任何元素可以选取时，元素和只能是 0，对应的方案数是 1，因此动态规划的边界条件是：
        //            dp[0][j]=
        //                1,j=0
        //                0,j>0
//        当1<=i<=n时，对于数组nums中的第i个元素num，遍历0<=j<=neg，计算值
        dp[0][0] = 1;
        for (int i = 1; i <= n; i++) {
            //与518零钱组合不同，这里的物品要从0开始遍历
            //            因为在518中，每一行第一列的值都是base case中确定的，没有必要再从0开始
            //            而这一题中的base case只能确定 dp[0][0] = 1，并没有更多的条件，所以每一行的列都要从0开始计算
            for (int j = 0; j <= neg; j++) {
                if (j >= nums[i - 1]) {
                    dp[i][j] = dp[i - 1][j] + dp[i - 1][j - nums[i - 1]];
                } else {
                    dp[i][j] = dp[i - 1][j];
                }
            }
        }
        return dp[n][neg];
    }
}
