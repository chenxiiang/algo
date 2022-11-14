package cx.leetcode.bytedance;

public class Solution37 {
    public void solveSudoku(char[][] board) {
        backtrack(board, 0, 0);
    }

    int row = 9, col = 9;

    private boolean backtrack(char[][] board, int i, int j) {
        if (j == col) {
            return backtrack(board, i + 1, 0);
        }
        if (i == row) {
            return true;
        }
        if (board[i][j] != '.') {
            return backtrack(board, i, j + 1);
        }
        for (char ch = '1'; ch <= '9'; ch++) {
            if (!isValid(board, i, j, ch)) {
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

    private boolean isValid(char[][] board, int row, int col, char ch) {
        for (int i = 0; i < 9; i++) {
            if (board[row][i] == ch) {
                return false;
            }
            if (board[i][col] == ch) {
                return false;
            }
            if (board[row / 3 * 3 + i / 3][col / 3 * 3 + i % 3] == ch) {
                return false;
            }
        }
        return true;
    }
}
