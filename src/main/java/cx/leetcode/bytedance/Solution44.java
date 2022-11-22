package cx.leetcode.bytedance;

public class Solution44 {
    public boolean isMatch(String s, String p) {
        int m = s.length(), n = p.length();
        boolean[][] f = new boolean[m + 1][n + 1];

        //都是空串，自然匹配
        f[0][0] = true;

        for (int i = 1; i <= n; i++) {
            //*可以匹配任意长度，空串也可以，先把base case求出来
            f[0][i] = f[0][i - 1] && p.charAt(i - 1) == '*';
        }

        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                //对应位置字母相同，或者模式串中是？可以匹配任何单一字符
                if (s.charAt(i - 1) == p.charAt(j - 1) || p.charAt(j - 1) == '?') {
                    f[i][j] = f[i - 1][j - 1];
                }
                if (p.charAt(j - 1) == '*') {
                    //字母不同，并且模式串也不是？，如果是*，则
                    //可以从上一行的任意一个true出发，也就是从上一个匹配的true向正下方走，因为可以匹配空串
                    //或者直接向右走，可以匹配任意长度的字符串
                    //在这两种情况中，只要有一种可以匹配则当前位置就可以匹配
                    f[i][j] = f[i - 1][j] || f[i][j - 1];
                }
            }
        }
        return f[m][n];
    }
}
