package cx.leetcode.graph;

import java.util.LinkedList;
import java.util.List;

public class Solution207 {
    boolean[] visited;
    boolean[] onPath;
    boolean hasCycle = false;

    public boolean canFinish(int numCourses, int[][] prerequisites) {
        List<Integer>[] graph = buildGraph(numCourses, prerequisites);
        visited = new boolean[numCourses];
        onPath = new boolean[numCourses];
        for (int i = 0; i < numCourses; i++) {
            traverse(graph, i);
        }
        return hasCycle;
    }

    private void traverse(List<Integer>[] graph, int s) {
        if (onPath[s]) {
            //出现环
            hasCycle = true;
        }
        if (visited[s] || hasCycle) {
            //节点已经遍历过或者出现环都结束
            return;
        }
        //节点标记为已遍历
        visited[s] = true;
        //开始遍历节点
        onPath[s] = true;
        for (int t : graph[s]) {
            traverse(graph, t);
        }
        onPath[s] = false;
    }

    private List<Integer>[] buildGraph(int numCourses, int[][] prerequisites) {
        List<Integer>[] graph = new LinkedList[numCourses];
        for (int i = 0; i < numCourses; i++) {
            graph[i] = new LinkedList<>();
        }
        for (int[] edge : prerequisites) {
            int from = edge[1], to = edge[0];
            graph[from].add(to);
        }
        return graph;
    }
}
