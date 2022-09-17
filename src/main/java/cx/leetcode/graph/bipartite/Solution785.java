package cx.leetcode.graph.bipartite;

import java.util.LinkedList;
import java.util.Queue;

public class Solution785 {
    private boolean ok = true;

    private boolean[] color;

    private boolean[] visited;

    public boolean isBipartite(int[][] graph) {
        int n = graph.length;
        color = new boolean[n];
        visited = new boolean[n];
        //有可能是非连通图，可能存在逗哥子图，把每个节点作为起点遍历一次，如果有一个子图不是二分图，整幅图都不是二分图
        for (int v = 0; v < n; v++) {
            if (!visited[v]) {
//                traverse(graph, v);
                bfs(graph, v);
            }
        }
        return ok;
    }

    private void traverse(int[][] graph, int v) {
        if (!ok) {
            return;
        }
        visited[v] = true;
        for (int neighbor : graph[v]) {
            if (!visited[neighbor]) {
                color[neighbor] = !color[v];
                traverse(graph, neighbor);
            } else {
                if (color[v] == color[neighbor]) {
                    ok = false;
                    return;
                }
            }
        }
    }

    private void bfs(int[][] graph, int start) {
        Queue<Integer> q = new LinkedList<>();
        visited[start] = true;
        q.offer(start);
        while (!q.isEmpty() && ok) {
            int v = q.poll();
            for (int w : graph[v]) {
                if (!visited[w]) {
                    color[w] = !color[v];
                    visited[w] = true;
                    q.offer(w);
                } else {
                    if (color[w] == color[v]) {
                        ok = false;
                        return;
                    }
                }
            }
        }
    }
}
