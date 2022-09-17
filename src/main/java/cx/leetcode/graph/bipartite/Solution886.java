package cx.leetcode.graph.bipartite;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Solution886 {
    boolean ok = true;
    boolean[] color;
    boolean[] visited;

    public boolean possibleBipartition(int n, int[][] dislikes) {
        List<Integer>[] graph = buildGraph(n, dislikes);
        color = new boolean[n + 1];
        visited = new boolean[n + 1];
        for (int i = 1; i <= n; i++) {
            if (!visited[i]) {
                traverse(graph, i);
//                bfs(graph, i);
            }
        }
        return ok;
    }

    private void bfs(List<Integer>[] graph, int start) {
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

    private void traverse(List<Integer>[] graph, int v) {
        if (!ok) {
            return;
        }
        visited[v] = true;
        for (int w : graph[v]) {
            if (!visited[w]) {
                color[w] = !color[v];
                traverse(graph, w);
            } else {
                if (color[w] == color[v]) {
                    ok = false;
                    return;
                }
            }
        }
    }

    private List<Integer>[] buildGraph(int n, int[][] dislikes) {
        List<Integer>[] graph = new LinkedList[n + 1];
        for (int i = 1; i <= n; i++) {
            graph[i] = new LinkedList<>();
        }

        for (int[] edge : dislikes) {
            int v = edge[0];
            int w = edge[1];
            graph[v].add(w);
            graph[w].add(v);
        }
        return graph;
    }
}
