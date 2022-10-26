package cx.leetcode.bytedance;

public class Solution79 {
    char[][] board;
    boolean[][] visited;
    char[] word;

    int[][] dirs = new int[][]{{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
    int row, col;

    public boolean exist(char[][] board, String word) {
        this.board = board;
        row = board.length;
        col = board[0].length;
        visited = new boolean[row][col];
        this.word = word.toCharArray();
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (board[i][j] != this.word[0]) {
                    continue;
                }
                if (findWord(i, j, 0)) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean findWord(int i, int j, int curr) {
        if (curr == word.length) {
            return true;
        }
        if (i < 0 || j < 0 || i >= row || j >= col) {
            return false;
        }
        if (board[i][j] != word[curr] || visited[i][j]) {
            //表格中的当前位置与单词的当前位置字母不同，或者表格中的当前位置已经访问过，false
            return false;
        }
        visited[i][j] = true;
        for (int[] dir : dirs) {
            if (findWord(i + dir[0], j + dir[1], curr + 1)) {
                return true;
            }
        }
        visited[i][j] = false;
        return false;
    }
}
