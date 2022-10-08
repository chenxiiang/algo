package cx.leetcode.island;

public class Solution1905 {
    //两个数组大小相等
    //在grid2中找grid1的子岛屿的数量
    //如果grid2中岛屿任何一块陆地在grid1中对应位置是水，那么这个岛屿肯定不是子岛屿，全部排除后，剩下的就是子岛屿
    public int countSubIslands(int[][] grid1, int[][] grid2) {
        int row = grid1.length, col = grid1[0].length;
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (grid1[i][j] == 0 && grid2[i][j] == 1) {
                    dfs(grid2, i, j);
                }
            }
        }
        int res = 0;
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (grid2[i][j] == 1) {
                    res++;
                    dfs(grid2, i, j);
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
