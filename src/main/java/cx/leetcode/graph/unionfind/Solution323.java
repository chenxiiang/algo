package cx.leetcode.graph.unionfind;

public class Solution323 {
    public int countComponents(int n, int[][] edges) {
        UF uf = new UF(n);
        for (int[] edge : edges) {
            int p = edge[0], q = edge[1];
            uf.union(p, q);
        }
        return uf.count();
    }
}
