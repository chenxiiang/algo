package cx.leetcode.dp;

/**
 * 给定两个单词 word1 和 word2 ，返回使得 word1 和  word2 相同所需的最小步数。只有删除操作
 */
public class Solution583 {
    /**
     * 偷懒的方法就是直接求最长公共子序列的长度lcs，然后len1-lcs+len2-lcs，就是所需的最小删除次数
     * <p>
     * 正常的方法就是继续dp，两种情况
     * 1、s1[i]==s2[j]：f[i][j] = f[i - 1][j - 1]，代表可以不用必然删掉 s1[i] 和 s2[j] 形成相同字符串；
     * 2、s1[i]!=s2[j]：f[i][j] = min(f[i - 1][j], f[i][j - 1])+1，代表至少一个删除 s1[i] 和 s2[j] 中的其中一个。
     */
    public int minDistance(String word1, String word2) {
        int m = word1.length(), n = word2.length();
        int[][] dp = new int[m + 1][n + 1];
        for (int i = 1; i <= m; i++) {
            dp[i][0] = i;
        }
        for (int j = 1; j <= n; j++) {
            dp[0][j] = j;
        }
        char c1, c2;
        for (int i = 1; i <= m; i++) {
            c1 = word1.charAt(i - 1);
            for (int j = 1; j <= n; j++) {
                c2 = word2.charAt(j - 1);
                if (c1 == c2) {
                    dp[i][j] = dp[i - 1][j - 1];
                } else {
                    dp[i][j] = Math.min(dp[i - 1][j], dp[i][j - 1]) + 1;
                }
            }
        }
        return dp[m][n];
    }
}
