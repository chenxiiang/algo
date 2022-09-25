package cx.leetcode.dp;

/**
 * 最长公共子序列
 */
public class Solution1143 {
    //    dp[i][j] ，其含义是在 A[0:i] 与 B[0:j] 之间匹配得到的想要的结果。
    public int longestCommonSubsequence(String text1, String text2) {
        int m = text1.length(), n = text2.length();
        if (n * m == 0) {
            return 0;
        }
        int[][] dp = new int[m + 1][n + 1];
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (text1.charAt(i - 1) == text2.charAt(j - 1)) {
//                    相等的情况容易理解，就看A[0:i] 与 B[0:j]的结果+1就行
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
//                    当 text1[i - 1] != text2[j - 1]时，说明两个子字符串的最后一位不相等，那么此时的状态 dp[i][j] 应该是 dp[i - 1][j] 和 dp[i][j - 1] 的最大值。
//                    举个例子，比如对于 ace和 bc而言，他们的最长公共子序列的长度等于 ① ace 和 b 的最长公共子序列长度0 与 ② ac 和 bc的最长公共子序列长度1 的最大值，即 1。
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }
        return dp[m][n];
    }
}
