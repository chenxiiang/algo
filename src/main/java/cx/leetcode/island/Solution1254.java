package cx.leetcode.island;

public class Solution1254 {
    /**
     * 封闭岛屿，就是不在数组边缘的岛，把边缘的岛都淹掉，剩下的就是封闭岛
     */
    public int closedIsland(int[][] grid) {
        int row = grid.length, col = grid[0].length;
        for (int i = 0; i < row; i++) {
            //淹掉最左边的岛
            dfs(grid, i, 0);
            //淹掉最右边的岛
            dfs(grid, i, col - 1);
        }
        for (int j = 0; j < col; j++) {
            dfs(grid, 0, j);
            dfs(grid, row - 1, j);
        }
        int res = 0;
        //遍历数组，剩下的岛屿都是封闭岛屿
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (grid[i][j] == 0) {
                    res++;
                    dfs(grid, i, j);
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
        if (grid[i][j] == 1) {
            return;
        }
        grid[i][j] = 1;
        dfs(grid, i + 1, j);
        dfs(grid, i, j + 1);
        dfs(grid, i - 1, j);
        dfs(grid, i, j - 1);
    }
}
