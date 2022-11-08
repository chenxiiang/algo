package cx.leetcode.bytedance;

public class Solution200 {
    //重点
    public int numIslands(char[][] grid) {
        int res = 0;
        int row = grid.length, col = grid[0].length;
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (grid[i][j] == '1') {
                    res++;
                    dfs(grid, i, j);
                }
            }
        }
        return res;
    }

    //从（i,j）开始，把所有与之相邻的陆地都变成海水
    public void dfs(char[][] grid, int i, int j) {
        int row = grid.length, col = grid[0].length;
        if (i < 0 || j < 0 || i >= row || j >= col) {
            return;
        }
        if (grid[i][j] == '0') {
            return;
        }
        grid[i][j] = '0';
        dfs(grid, i + 1, j);
        dfs(grid, i, j + 1);
        dfs(grid, i - 1, j);
        dfs(grid, i, j - 1);
    }
}
