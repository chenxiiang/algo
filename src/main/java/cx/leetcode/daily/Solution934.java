package cx.leetcode.daily;

import java.util.ArrayDeque;
import java.util.Deque;

public class Solution934 {
    int marked = 2;
    Deque<int[]> points = new ArrayDeque<>();
    int[][] coordinates = {{-1, 0}, {1, 0}, {0, 1}, {0, -1}}; // 上、下、右、左四个方向

    public int shortestBridge(int[][] grid) {
        int result = 0;
        int row = grid.length, col = grid[0].length;
        boolean findIsland = false;     //只要找到两个岛屿中的一个，就设置为true，并结束步骤1中的双层循环
        for (int i = 0; !findIsland && i < row; i++) {
            for (int j = 0; !findIsland && j < col; j++) {
                if (grid[i][j] == 1) {
                    findIsland = true;
                    dfs(grid, i, j);
                }
            }
        }
        while (!points.isEmpty()) {
            result++;       //扩展层数
            int num = points.size();
            for (int i = 0; i < num; i++) {
                //point就是第一个岛外围的邻格
                int[] point = points.pollFirst();
                for (int[] c : coordinates) {
                    int nextX = point[0] + c[0], nextY = point[1] + c[1];
                    if (isLegal(grid, nextX, nextY) && grid[nextX][nextY] == 0) {
                        points.addLast(new int[]{nextX, nextY});
                        grid[nextX][nextY] = 2;
                    } else if (isLegal(grid, nextX, nextY) && grid[nextX][nextY] == 1) {
                        return result;
                    }
                }
            }
        }
        return result;
    }

    private void dfs(int[][] grid, int i, int j) {
        if (!isLegal(grid, i, j) || grid[i][j] == marked) {
            return;
        }
        if (grid[i][j] == 0) {
            grid[i][j] = marked;
            points.addLast(new int[]{i, j});
            return;
        }
        grid[i][j] = marked;
        dfs(grid, i + 1, j);
        dfs(grid, i, j + 1);
        dfs(grid, i - 1, j);
        dfs(grid, i, j - 1);
    }

    private boolean isLegal(int[][] grid, int row, int col) {
        return row >= 0 && col >= 0 && row < grid.length && col < grid[0].length;
    }
}
