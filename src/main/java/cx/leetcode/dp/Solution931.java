package cx.leetcode.dp;

public class Solution931 {
    public int minFallingPathSum(int[][] matrix) {
        int n = matrix.length;
        //可以用新数组dp，也可以直接在原数组上做
        //dp[i][j] 表示 到达该点的最小值
        // int[][] dp = new int[n][n];
        // for(int i = 0; i < n; i++){
        //     dp[0][i] = matrix[0][i];
        // }
        for (int i = 1; i < n; i++) {
            for (int j = 0; j < n; j++) {
                //对j=0 和 j=n-1 分别处理
                if (j == 0) {
                    matrix[i][j] = Math.min(matrix[i - 1][j], matrix[i - 1][j + 1]) + matrix[i][j];
                } else if (j == n - 1) {
                    matrix[i][j] = Math.min(matrix[i - 1][j], matrix[i - 1][j - 1]) + matrix[i][j];
                } else {
                    matrix[i][j] = Math.min(Math.min(matrix[i - 1][j], matrix[i - 1][j - 1]), matrix[i - 1][j + 1])
                            + matrix[i][j];
                }

            }
        }
        int min = matrix[n - 1][0];
        for (int i = 1; i < n; i++) {
            min = Math.min(min, matrix[n - 1][i]);
        }
        return min;
    }
}
