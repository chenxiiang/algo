package cx.leetcode.graph;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Solution797 {
    private List<List<Integer>> res = new ArrayList<>();

    public List<List<Integer>> allPathsSourceTarget(int[][] graph) {
        LinkedList<Integer> path = new LinkedList<>();
        traverse(graph, 0, path);
        return res;
    }

    private void traverse(int[][] graph, int s, LinkedList<Integer> path) {
        path.addLast(s);
        int n = graph.length;
        if (s == n - 1) {
            res.add(new LinkedList<>(path));
        }
        for (int v : graph[s]) {
            traverse(graph, v, path);
        }
        path.removeLast();
    }

}
