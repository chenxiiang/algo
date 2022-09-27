package cx.leetcode.dp;

import java.util.Arrays;

public class Solution174 {
    public int calculateMinimumHP(int[][] dungeon) {
//        这题如果从左上往右下，不能推出正确的最优路径，因为在过程中有两个变量都会影响后续的决策：从出发点到当前的路径和以及从出发点到当前所需的最小初始值
//        所以要从右下往左上进行动态规划
//        令dp[i][i]表示从[i,j]到达终点所需的最下初始值，换句话说，当我们到达坐标 (i,j)时，如果此时我们的路径和不小于 dp[i][j]，我们就能到达终点。
//        这样的话就不用关心路径和，只关注最小初始值
//        对于dp[i][j]，只要关心dp[i][j+1]和dp[i+1][j]中的最小值minn
//        记当前格子的值为dungeon(i,j)，那么在坐标 (i,j) 的初始值只要达到 minn−dungeon(i,j) 即可。同时，初始值还必须大于等于 1。这样我们就可以得到状态转移方程：
//          dp[i][j]=max(min(dp[i+1][j],dp[i][j+1])−dungeon(i,j),1)，最终答案即为dp[0][0]
        int m = dungeon.length, n = dungeon[0].length;
        int[][] dp = new int[m + 1][n + 1];
        for (int i = 0; i <= m; i++) {
            Arrays.fill(dp[i], Integer.MAX_VALUE);
        }
//        按照分析，这两个各自是越界的，给他们赋值为1，也就是在第一次遍历时要保证dp[m-1][n-1]值为1，骑士在最后一个格子血量为1
        dp[m][n - 1] = dp[m - 1][n] = 1;
        for (int i = m - 1; i >= 0; i--) {
            for (int j = n - 1; j >= 0; j--) {
                int minn = Math.min(dp[i + 1][j], dp[i][j + 1]);
//                要时刻保持血量不少于1
                dp[i][j] = Math.max(minn - dungeon[i][j], 1);
            }
        }
        return dp[0][0];
    }
}
