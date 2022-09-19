package cx.leetcode.graph.mst;

import cx.leetcode.graph.unionfind.UF;

public class Solution261 {
    public boolean validTree(int n, int[][] edges) {
        UF uf = new UF(n);
        for (int[] edge : edges) {
            int p = edge[0], q = edge[1];
            if (uf.connected(p, q)) {
                return false;
            }
            uf.union(p, q);
        }
        return uf.count() == 1;
    }
}
