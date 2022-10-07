package cx.leetcode.backtrack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Solution51 {
    List<List<String>> res = new ArrayList<>();

    public List<List<String>> solveNQueens(int n) {
        char[][] board = new char[n][n];
        // "." 表示空，"Q"表示皇后，初始化棋盘
        for (char[] c : board) {
            Arrays.fill(c, '.');
        }
        backtrack(board, 0);
        return res;
    }

    private void backtrack(char[][] board, int row) {
        if (row == board.length) {
            res.add(charToList(board));
            return;
        }
        int n = board[row].length;
        for (int col = 0; col < n; col++) {
            if (!isValid(board, row, col)) {
                continue;
            }
            board[row][col] = 'Q';
            backtrack(board, row + 1);
            board[row][col] = '.';
        }
    }

    //是否可以在board[row][col]放置皇后
    private boolean isValid(char[][] board, int row, int col) {
        //因为是按行放置的，所以不检查行
        //因为行是从上往下放的，所以不检查下方和左下角以及右下角
        int n = board.length;
        //查看同一列
        for (int i = 0; i <= row; i++) {
            if (board[i][col] == 'Q') {
                return false;
            }
        }
        //查看右上方
        for (int i = row - 1, j = col + 1; i >= 0 && j < n; i--, j++) {
            if (board[i][j] == 'Q') {
                return false;
            }
        }
        //查看左上方
        for (int i = row - 1, j = col - 1; i >= 0 && j >= 0; i--, j--) {
            if (board[i][j] == 'Q') {
                return false;
            }
        }
        return true;
    }

    public List<String> charToList(char[][] board) {
        List<String> list = new ArrayList<>();
        for (char[] c : board) {
            list.add(String.copyValueOf(c));
        }
        return list;
    }
}
