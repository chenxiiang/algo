package cx.leetcode.dp;

import java.util.Arrays;

public class Solution887 {
    //    使用动态规划来做这道题，状态可以表示成 (k,n)，其中 k为鸡蛋数，n 为楼层数。当我们从第 x 楼扔鸡蛋的时候：
//如果鸡蛋不碎，那么状态变成 (k,n−x)，即我们鸡蛋的数目不变，但答案只可能在上方的 n−x 层楼了。也就是说，我们把原问题缩小成了一个规模为 (k,n−x) 的子问题；
//如果鸡蛋碎了，那么状态变成 (k−1,x−1)，即我们少了一个鸡蛋，但我们知道答案只可能在第 x 楼下方的 x−1 层楼中了。也就是说，我们把原问题缩小成了一个规模为 (k−1,x−1) 的子问题。
//dp(k,n) 是一个关于 n 的单调递增函数，也就是说在鸡蛋数k 固定的情况下，楼层数 n 越多，需要的步数一定不会变少
    public int superEggDrop(int k, int n) {
        // m 最多不会超过 n 次（线性扫描）
        memo = new int[k + 1][n + 1];
        for (int[] row : memo) {
            Arrays.fill(row, -666);
        }
        return dp(k, n);
    }

    int[][] memo;

    int dp(int k, int n) {
        //base case
        if (k == 1) {
//            如果只有一个鸡蛋，只能从下往上线性扫描所有楼层
            return n;
        }
        if (n == 0) {
//            楼层为0不用扔鸡蛋
            return 0;
        }
        if (memo[k][n] != -666) {
            return memo[k][n];
        }
        // 状态转移方程，也是线性扫描的过程
//        int res = Integer.MAX_VALUE;
//        for (int i = 1; i <= n; i++) {
//            // 在所有楼层进行尝试，取最少扔鸡蛋次数
//            res = Math.min(
//                    res,
//                    // 碎和没碎取最坏情况
//                    Math.max(dp(k, n - i), dp(k - 1, i - 1)) + 1
//            );
//        }

//用二分法代替线性搜索
        int res = Integer.MAX_VALUE;
        int low = 1, high = n;
        while (low <= high) {
            int mid = low + (high - low) / 2;
// 鸡蛋在mid层碎了和没碎两种情况下的楼层
            int broken = dp(k - 1, mid - 1);
            int notBroken = dp(k, n - mid);
// int res = min(max(碎，没碎)+1)
//dp(k,n) 是一个关于 n 的单调递增函数，也就是说在鸡蛋数k 固定的情况下，楼层数 n 越多，需要的步数一定不会变少
// 用i做横坐标，纵坐标为dp(k,n)，dp(k, n - i)随着i的递增而递减，dp(k - 1, i - 1)随着i的递增而递增，这两个函数会有一个交点，就是用二分求交点
// 一个递增一个递减，要找一个位置使它们的最大值最小，这个点就是交点
            if (broken > notBroken) {
                high = mid - 1;
                res = Math.min(res, broken + 1);
            } else {
                low = mid + 1;
                res = Math.min(res, notBroken + 1);
            }
        }
        memo[k][n] = res;
        return res;
    }
}
