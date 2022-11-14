package cx.leetcode.bytedance;

public class Solution36 {
    public boolean isValidSudoku(char[][] board) {
        boolean[][] row = new boolean[9][9];    //行
        boolean[][] col = new boolean[9][9];    //列
        boolean[][] block = new boolean[9][9];  //3x3小方格
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (board[i][j] != '.') {
                    int num = board[i][j] - '1';
                    int blockIdx = i / 3 * 3 + j / 3;
                    if (row[i][num] || col[j][num] || block[blockIdx][num]) {
                        return false;
                    } else {
                        row[i][num] = true;
                        col[j][num] = true;
                        block[blockIdx][num] = true;
                    }
                }
            }
        }
        return true;
    }
}
