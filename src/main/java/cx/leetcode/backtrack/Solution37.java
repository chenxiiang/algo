package cx.leetcode.backtrack;

public class Solution37 {
    /**
     * 数独
     */
    public void solveSudoku(char[][] board) {
        backtrack(board, 0, 0);
    }

    public boolean backtrack(char[][] board, int i, int j) {
        int row = 9, col = 9;
        if (j == col) {
            //穷举到最后一列就换下一行
            return backtrack(board, i + 1, 0);
        }
        if (i == row) {
            //到达最后一行，遍历完成
            return true;
        }
        if (board[i][j] != '.') {
            //是预设的数字，直接跳过
            return backtrack(board, i, j + 1);
        }
        for (char ch = '1'; ch <= '9'; ch++) {
            if (!isValid(board, i, j, ch)) {
                //遇到不合法的数字就跳过
                continue;
            }
            board[i][j] = ch;
            if (backtrack(board, i, j + 1)) {
                return true;
            }
            board[i][j] = '.';
        }
        return false;
    }

    boolean isValid(char[][] board, int row, int col, char ch) {
        for (int i = 0; i < 9; i++) {
            if (board[row][i] == ch) {
                //行存在重复
                return false;
            }
            if (board[i][col] == ch) {
                //列存在重复
                return false;
            }
            //row/3 col/3是为了定位在哪个小方框中，加号得到小方框中的坐标
            if (board[(row / 3) * 3 + i / 3][(col / 3) * 3 + i % 3] == ch) {
                return false;
            }
        }
        return true;
    }
}
