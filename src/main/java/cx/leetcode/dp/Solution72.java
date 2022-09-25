package cx.leetcode.dp;

public class Solution72 {
    /**
     * https://leetcode.cn/problems/edit-distance/solution/zi-di-xiang-shang-he-zi-ding-xiang-xia-by-powcai-3/
     * 对于边界情况，一个空串和一个非空串的编辑距离为 D[i][0] = i 和 D[0][j] = j，D[i][0] 相当于对 word1 执行 i 次删除操作，D[0][j] 相当于对 word1执行 j 次插入操作。
     */
    public int minDistance(String word1, String word2) {
        // dp[i][j] 代表 word1 到 i 位置转换成 word2 到 j 位置需要最少步数
        // 所以，当 word1[i] == word2[j]，dp[i][j] = dp[i-1][j-1]；
        // 当 word1[i] != word2[j]，dp[i][j] = min(dp[i-1][j-1], dp[i-1][j], dp[i][j-1]) + 1
        // 其中，dp[i-1][j-1] 表示替换操作，dp[i-1][j] 表示删除操作，dp[i][j-1] 表示插入操作。

        //  dp[i-1][j-1]+1  带上+1可能更好理解，两个单词的索引同时变了，+1就是那个替换操作 解释：
        //  直接把 s1[i] 替换成 s2[j]，这样它俩就匹配了
        //  同时前移 i，j 继续对比
        //  操作数加1
        //  dp[i-1][j]+1 用单词1的i-1位置的值，就相当于把i的字符删了 解释：
        //  直接把 s[i] 这个字符删掉
        //  前移 i，继续跟 j 对比
        //  操作数加1
        //  dp[i][j-1]+1  解释：
        //  直接在 s1[i] 插入一个和 s2[j] 一样的字符
        //  那么 s2[j] 就被匹配了，前移 j，继续跟 i 对比
        //  操作数加1

        int n1 = word1.length(), n2 = word2.length();
        int[][] dp = new int[n1 + 1][n2 + 1];
        for (int i = 1; i <= n1; i++) {
            dp[i][0] = dp[i - 1][0] + 1;
        }
        for (int j = 1; j <= n2; j++) {
            dp[0][j] = dp[0][j - 1] + 1;
        }
        for (int i = 1; i <= n1; i++) {
            for (int j = 1; j <= n2; j++) {
                if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
                    // 解释：
                    // 本来就相等，不需要任何操作
                    // s1[0..i] 和 s2[0..j] 的最小编辑距离等于
                    // s1[0..i-1] 和 s2[0..j-1] 的最小编辑距离
                    // 也就是说 dp(i, j) 等于 dp(i-1, j-1)
                    dp[i][j] = dp[i - 1][j - 1];
                } else {
                    // 不相同的情况上，有三种操作的可能 1）删除左边 2） 删除右边 3）替换/  插入跟删除本质是同一个种操作
                    // 插入等价于删除，在字符串1里插入等价于在字符串2删除对应的字符。操作只有删除和替换。
                    dp[i][j] = Math.min(Math.min(dp[i - 1][j - 1], dp[i][j - 1]), dp[i - 1][j]) + 1;
                }
            }
        }
        return dp[n1][n2];
    }
}
