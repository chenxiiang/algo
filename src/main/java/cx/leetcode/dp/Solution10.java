package cx.leetcode.dp;

public class Solution10 {
    public boolean isMatch(String string, String pattern) {
        //https://leetcode.cn/problems/regular-expression-matching/solution/shou-hui-tu-jie-wo-tai-nan-liao-by-hyj8/
        char[] s = string.toCharArray(), p = pattern.toCharArray();
        //dp[i][j]:表示s的前i个字符，p的前j个字符是否能够匹配
        boolean[][] dp = new boolean[s.length + 1][p.length + 1];
        //base case:1、二者都为空串，可以匹配
        dp[0][0] = true;
        //    2、p为空，s不为空，必为false，默认值就是，不用管
        //    3、s为空，p不为空，因为*可以匹配0个字符，所以可能为true
        for (int j = 2; j <= p.length; j++) {
            //题目保证*前面一定有一个有效字符
            if (p[j - 1] == '*') {
                //为什么要用j-2的值而不是直接为true？
                //因为此时s是空串，当*匹配0次时，也就是说正则式一共两个字符，*是第二个，只有这时才是true，为什么不直接写，因为存在这样一种情况：
                //p长度为偶数，且所有偶数的位置上都是*，那么它的j-2都是true
                //如果中间有任何一个打破了这样的排列，那么就是false
                //所以要看它的前2位处
                dp[0][j] = dp[0][j - 2];
            }
        }
        for (int i = 1; i <= s.length; i++) {
            for (int j = 1; j <= p.length; j++) {
                if (s[i - 1] == p[j - 1] || p[j - 1] == '.') {
                    //右端的字符是匹配的，那么，大问题的答案 = 剩余子串是否匹配
                    dp[i][j] = dp[i - 1][j - 1];
                } else if (p[j - 1] == '*') {
                    // 不匹配的时候看模式串最后一位是不是*
                    // 模式串末尾是*
                    // 模式串前一位能够跟文本串的末尾匹配
                    if (s[i - 1] == p[j - 2] || p[j - 2] == '.') {
                        dp[i][j] = dp[i][j - 2]       //*匹配0次
                                || dp[i - 1][j];            //*匹配1次或多次（多次要看后面的循环结果）
                    } else {
                        // 模式串*的前一个字符跟文本串末尾不匹配
                        dp[i][j] = dp[i][j - 2];
                    }
                }
            }
        }
        return dp[s.length][p.length];
    }
}
