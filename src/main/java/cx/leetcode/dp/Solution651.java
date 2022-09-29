package cx.leetcode.dp;

public class Solution651 {
    public int maxA(int n) {
        int[] dp = new int[n + 1];
        dp[0] = 0;
        for (int i = 1; i <= n; i++) {
            //按A键
            dp[i] = dp[i - 1] + 1;
            for (int j = 2; j < i; j++) {
                //将j作为cv的起点，前面两个操作是ca cc
                //全选&复制 dp[j-2]，连续粘贴i-j次，屏幕上共dp[j-2]*(i-j+1)，i-j是cv的次数，+1是dp[j-2]的值，这个值表示开始ca时已有的A的个数
                dp[i] = Math.max(dp[i], dp[j - 2] * (i - j + 1));
            }
        }
        return dp[n];
    }
}
