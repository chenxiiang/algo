package cx.leetcode.bytedance;

public class Solution10 {
    //重点
    public boolean isMatch(String string, String pattern) {
        char[] s = string.toCharArray(), p = pattern.toCharArray();
        //dp[i][j]:表示s的前i个字符，p的前j个字符是否能够匹配
        boolean[][] dp = new boolean[s.length + 1][p.length + 1];
        dp[0][0] = true;        //二者都是空串，可以匹配
        //p为空，s不为空，必为false，默认值就是
        //s为空，p不为空，*可以匹配0个字符，可能是true
        //保证*号前面一定有一个有效字符，0位置表示空串，所以从索引2开始
        for (int i = 2; i <= p.length; i++) {
            if (p[i - 1] == '*') {
                //奇数位置都是false，容易理解
                //偶数位置并不全是true，如果中间有一个位置不是*，那么后面的偶数位置都跟它一样了
                dp[0][i] = dp[0][i - 2];//星号前面一定有一个字符，所以奇数位置的星号是不考虑的，就算是*也是默认值false
            }
        }
        //以上都是base case

        for (int i = 1; i <= s.length; i++) {
            for (int j = 1; j <= p.length; j++) {
                if (s[i - 1] == p[j - 1] || p[j - 1] == '.') {
                    dp[i][j] = dp[i - 1][j - 1];        //当前位置能匹配，直接把前面的状态向后转移
                } else if (p[j - 1] == '*') {
                    //不能直接匹配的，并且模式串最后一位是 *
                    //1、模式串前一位能够跟文本串的末尾匹配
                    if (s[i - 1] == p[j - 2] || p[j - 2] == '.') {
                        //匹配0次  ||  匹配1次或多次
                        dp[i][j] = dp[i][j - 2] || dp[i - 1][j];
                        //匹配0次的容易理解
                        //匹配1次或多次的情况，用一个具体的例子来看,aaaaab,a*b，具体是匹配1次还是匹配多次，看s的索引i的具体位置，多次的情况就是不断的从s末尾拿掉一个字母，也就是看它的前一位跟模式串是否匹配，一直到匹配1次的情况，匹配和不匹配的情况只要有一个满足就可以，所以是或的关系
                    } else {
                        //2、模式串中*的前一位跟文本串的末尾不匹配，只能匹配0次
                        dp[i][j] = dp[i][j - 2];
                    }
                }
            }
        }
        return dp[s.length][p.length];
    }
}
