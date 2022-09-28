package cx.leetcode.dp;

import java.util.*;

public class Solution787 {
    //s1 s2是dst前面的两个点，w1和w2分别为它们到dst的路径权重
    //minPath(src, dst) = min(
    //     minPath(src, s1) + w1,
    //     minPath(src, s2) + w2
    // )

    int src, dst;

    //如果某个节点同时指向两个其他节点，那么这两个节点就有相同的一个入度节点，就会产生重复的递归计算
    int[][] memo;

    // 哈希表记录每个点的入度
    // to -> [from, price]
    Map<Integer, List<int[]>> indegree;

    public int findCheapestPrice(int n, int[][] flights, int src, int dst, int k) {
        //k次中转，也就是可以经过k+1条边
        k++;
        this.src = src;
        this.dst = dst;
        //k从1开始，最大值+1
        memo = new int[n][k + 1];
        for (int[] row : memo) {
            Arrays.fill(row, Integer.MIN_VALUE);
        }
        indegree = new HashMap<>();
        //flights[i] = [fromi, toi, pricei]
        for (int[] flight : flights) {
            int from = flight[0];
            int to = flight[1];
            int price = flight[2];
            indegree.putIfAbsent(to, new ArrayList<>());
            indegree.get(to).add(new int[]{from, price});
        }
        return dp(dst, k);
    }

    //从 src 出发，k 步之内到达 s 的最小成本
    int dp(int s, int k) {
        //base case
        if (s == src) {
            return 0;
        }
        if (k == 0) {
            return -1;
        }
        if (memo[s][k] != Integer.MIN_VALUE) {
            return memo[s][k];
        }
        int res = Integer.MAX_VALUE;
        if (indegree.containsKey(s)) {
            //当s有入度节点，分解为子问题
            for (int[] v : indegree.get(s)) {
                int from = v[0];
                int price = v[1];
                // 从 src 到达相邻的入度节点所需的最短路径权重
                int sub = dp(from, k - 1);
                //跳过无效的情况
                if (sub != -1) {
                    res = Math.min(res, sub + price);
                }
            }
        }
        memo[s][k] = res == Integer.MAX_VALUE ? -1 : res;
        return memo[s][k];
    }
}
