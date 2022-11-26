package cx.leetcode.bytedance;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Solution52 {
    List<List<String>> res = new ArrayList<>();

    public int totalNQueens(int n) {
        char[][] board = new char[n][n];
        for (char[] c : board) {
            Arrays.fill(c, '.');
        }
        backtrack(board, 0);
        return res.size();
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

    private boolean isValid(char[][] board, int row, int col) {
        int n = board.length;
        //同一列
        for (int i = 0; i <= row; i++) {
            if (board[i][col] == 'Q') {
                return false;
            }
        }
        //右上方
        for (int i = row - 1, j = col + 1; i >= 0 && j < n; i--, j++) {
            if (board[i][j] == 'Q') {
                return false;
            }
        }

        for (int i = row - 1, j = col - 1; i >= 0 && j >= 0; i--, j--) {
            if (board[i][j] == 'Q') {
                return false;
            }
        }
        return true;
    }

    private List<String> charToList(char[][] board) {
        List<String> list = new ArrayList<>();
        for (char[] c : board) {
            list.add(String.copyValueOf(c));
        }
        return list;
    }
}
