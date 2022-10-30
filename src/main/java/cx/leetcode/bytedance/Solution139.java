package cx.leetcode.bytedance;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Solution139 {
    /**
     * s[0:i] 子串对应 dp[i+1] ，它是否为 true（s[0:i]能否 break），取决于两点：
     * 它的前缀子串 s[0:j-1] 的 dp[j]，是否为 true。
     * 剩余子串 s[j:i]，是否是单词表的单词。
     */
    public boolean wordBreak(String s, List<String> wordDict) {
        Set<String> wordSet = new HashSet<>(wordDict);
        int len = s.length();
        boolean[] dp = new boolean[len + 1];
        dp[0] = true;
        for (int i = 1; i <= len; i++) {
            for (int j = i - 1; j >= 0; j--) {
                if (dp[i]) {
                    break;
                }
                if (!dp[j]) {
                    //如果dp[j]是false，则dp[i]不可能为true
                    continue;
                }
                String suffix = s.substring(j, i);
                if (wordSet.contains(suffix) && dp[j]) {
                    //剩余子串在单词表中&&前缀子串可以由单词表得到
                    dp[i] = true;
                    break;
                }
            }
        }
        return dp[len];
    }
}
