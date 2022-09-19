package cx.leetcode.graph.mst;

import cx.leetcode.graph.unionfind.UF;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Solution1584 {
    public int minCostConnectPoints(int[][] points) {
        int n = points.length;
        List<int[]> edges = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                int xi = points[i][0], yi = points[i][1];
                int xj = points[j][0], yj = points[j][1];
                edges.add(new int[]{i, j, Math.abs(xi - xj) + Math.abs(yi - yj)});
            }
        }
        Collections.sort(edges, (a, b) -> a[2] - b[2]);
        UF uf = new UF(n);
        int mst = 0;
        for (int[] edge : edges) {
            int u = edge[0];
            int v = edge[1];
            int weight = edge[2];
            if (uf.connected(u, v)) {
                continue;
            }
            uf.union(u, v);
            mst += weight;
        }
        return mst;
    }
}
