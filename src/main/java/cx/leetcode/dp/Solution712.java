package cx.leetcode.dp;

public class Solution712 {
    //跟72. 编辑距离的思路差不多，区别就是把删除的个数改成了删除的字符之和
    //https://leetcode.cn/problems/minimum-ascii-delete-sum-for-two-strings/solution/liang-ge-zi-fu-chuan-de-zui-xiao-asciish-xllf/
    public int minimumDeleteSum(String s1, String s2) {
        int m = s1.length(), n = s2.length();
        char[] c1 = s1.toCharArray(), c2 = s2.toCharArray();
        int[][] dp = new int[m + 1][n + 1];
        //写出来看起来比较直观
        dp[0][0] = 0;
        for (int i = 1; i <= m; i++) {
            dp[i][0] = dp[i - 1][0] + c1[i - 1];
        }
        for (int j = 1; j <= n; j++) {
            dp[0][j] = dp[0][j - 1] + c2[j - 1];
        }
        //    再处理字符都不为空的情况
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (c1[i - 1] == c2[j - 1]) {
                    dp[i][j] = dp[i - 1][j - 1];
                } else {
                    dp[i][j] = Math.min(dp[i - 1][j] + c1[i - 1], dp[i][j - 1] + c2[j - 1]);
                }
            }
        }
        return dp[m][n];
    }
}
