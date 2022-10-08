package cx.leetcode.island;

public class Solution1020 {
    /**
     * 飞地的数量
     * 就是求封闭岛屿的总面积
     */
    public int numEnclaves(int[][] grid) {
        int row = grid.length, col = grid[0].length;
        for (int i = 0; i < row; i++) {
            dfs(grid, i, 0);
            dfs(grid, i, col - 1);
        }

        for (int j = 0; j < col; j++) {
            dfs(grid, 0, j);
            dfs(grid, row - 1, j);
        }

        int res = 0;
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (grid[i][j] == 1) {
                    res++;
                }
            }
        }
        return res;
    }

    //从（i,j）开始，把所有与之相邻的陆地都变成海水
    public void dfs(int[][] grid, int i, int j) {
        int row = grid.length, col = grid[0].length;
        if (i < 0 || j < 0 || i >= row || j >= col) {
            return;
        }
        if (grid[i][j] == 0) {
            return;
        }
        grid[i][j] = 0;
        dfs(grid, i + 1, j);
        dfs(grid, i, j + 1);
        dfs(grid, i - 1, j);
        dfs(grid, i, j - 1);
    }
}
