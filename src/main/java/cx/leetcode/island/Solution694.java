package cx.leetcode.island;

import java.util.HashSet;
import java.util.Set;

public class Solution694 {
    /**
     * 计算不同形状的数量
     * 相同是指岛屿不经过旋转和翻转，只通过平移就可以和另一个岛屿完全重合
     * 代码中看，形状相同的岛屿则dfs的遍历顺序肯定是相同的，因为遍历顺序在函数中是写死的
     * <p>
     * 分别用 1, 2, 3, 4 代表上下左右，用 -1, -2, -3, -4 代表上下左右的撤销
     * 如果没有撤销操作，会造成不同的形状有相同的遍历顺序
     * 比如[[1,1,0],[0,1,1],[0,0,0],[1,1,1],[0,1,0]]，如果不撤销，得到的方向都是右下右
     */
    public int numDistinctIslands(int[][] grid) {
        int row = grid.length, col = grid[0].length;
        Set<String> islands = new HashSet<>();
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (grid[i][j] == 1) {
                    StringBuilder sb = new StringBuilder();
                    //初始方向无所谓
                    dfs(grid, i, j, sb, Integer.MAX_VALUE);
                    islands.add(sb.toString());
                }
            }
        }
        return islands.size();
    }

    public void dfs(int[][] grid, int i, int j, StringBuilder sb, int dir) {
        int row = grid.length, col = grid[0].length;
        if (i < 0 || j < 0 || i >= row || j >= col || grid[i][j] == 0) {
            return;
        }

        //前序遍历位置，进入（i,j）
        grid[i][j] = 0;
        sb.append(dir).append(',');

        dfs(grid, i - 1, j, sb, 1);     //上
        dfs(grid, i + 1, j, sb, 2);     //下
        dfs(grid, i, j - 1, sb, 3);     //左
        dfs(grid, i, j + 1, sb, 4);     //右

        //后续遍历位置，离开（i,j）
        sb.append(-dir).append(',');
    }
}
