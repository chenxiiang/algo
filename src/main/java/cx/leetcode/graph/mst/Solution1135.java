package cx.leetcode.graph.mst;

import cx.leetcode.graph.unionfind.UF;

import java.util.Arrays;

public class Solution1135 {
    //最小生成树，两个节点能连接的条件：
    //1、首先要可以形成一棵树，也就是不能有环，如果两个节点已经在同一个连通分量中，那么连接后一定会产生环
    //2.边的权重要用贪心算法来找
    //kruskal算法是把所有边的权重排序，然后贪心从中选出最小的，看最后能不能组成一棵树
    public int minimumCost(int n, int[][] connections) {
        UF uf = new UF(n + 1);
        Arrays.sort(connections, (a, b) -> a[2] - b[2]);
        int mst = 0;
        for (int[] edge : connections) {
            int u = edge[0];
            int v = edge[1];
            int weight = edge[2];
            if (uf.connected(u, v)) {
                continue;
            }
            mst += weight;
            uf.union(u, v);
        }
        //因为节点0没有使用，所以等于2说明所有节点连通
        return uf.count() == 2 ? mst : -1;
    }
}
