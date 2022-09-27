package cx.leetcode.dp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * hard
 */
public class Solution514 {
    //字符->索引列表
    Map<Character, ArrayList<Integer>> charMap;
    int[][] memo;

    public int findRotateSteps(String ring, String key) {
        int m = ring.length(), n = key.length();
        memo = new int[m][n];
        this.charMap = new HashMap<>();
        //记录圆环上字符到索引的映射
        for (int i = 0; i < m; i++) {
            char ch = ring.charAt(i);
            charMap.putIfAbsent(ch, new ArrayList<>());
            charMap.get(ch).add(i);
        }
        // 圆盘指针最初指向 12 点钟方向，
        // 从第一个字符开始输入 key
        return dp(ring, 0, key, 0) + n;
    }

    // 计算圆盘指针在 ring[i]，输入 key[j..] 的最少操作数
    int dp(String ring, int i, String key, int j) {
        if (j == key.length()) {
            //base case 完成输入
            return 0;
        }
        if (memo[i][j] != 0) {
            return memo[i][j];
        }
        int n = ring.length();
        int res = Integer.MAX_VALUE;
        //ring上可能有个多个字符key[i]
        for (int k : charMap.get(key.charAt(j))) {
            // 拨动指针的次数
            int delta = Math.abs(k - i);
            // 选择顺时针还是逆时针
            delta = Math.min(delta, n - delta);
            // 将指针拨到 ring[k]，继续输入 key[j+1..]
            int subProblem = dp(ring, k, key, j + 1);
            // 选择「整体」操作次数最少的
            res = Math.min(res, delta + subProblem);
        }
        // 将结果存入备忘录
        memo[i][j] = res;
        return res;
    }
}
