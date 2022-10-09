package cx.leetcode.bfs;

import java.util.ArrayDeque;
import java.util.HashSet;
import java.util.Queue;
import java.util.Set;

public class Solution773 {
    public int slidingPuzzle(int[][] board) {
        int row = 2, col = 3;
        String target = "123450";
        StringBuilder sb = new StringBuilder();
        //将数组转化为字符串作为bfs起点
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                sb.append(board[i][j]);
            }
        }
        String start = sb.toString();
        //正确结果的二维数组转为一维后，在一维字符串中，索引i在二维数组中的相邻索引为neighbor[i]
//        这道题是给定的数组所以可以直接写死，对于一个 m x n 的二维数组，如果二维数组中的某个元素 e 在一维数组中的索引为 i
//        那么 e 的左右相邻元素在一维数组中的索引就是 i - 1 和 i + 1
//        而 e 的上下相邻元素在一维数组中的索引就是 i - n 和 i + n，其中 n 为二维数组的列数。
        int[][] neighbor = new int[][]{
                {1, 3},
                {0, 4, 2},
                {1, 5},
                {0, 4},
                {3, 1, 5},
                {4, 2}
        };

        Queue<String> q = new ArrayDeque<>();
        Set<String> visited = new HashSet<>();
        q.offer(start);
        visited.add(start);
        int step = 0;
        while (!q.isEmpty()) {
            int size = q.size();
            for (int i = 0; i < size; i++) {
                String curr = q.poll();
                if (target.equals(curr)) {
                    return step;
                }
                //找到数字0的索引
                int index = curr.indexOf('0');
                //找到0的位置
//                for (; curr.charAt(index) != '0'; index++) ;
                //将数字0和相邻的数字交换位置
                for (int adj : neighbor[index]) {
                    String newBoard = swap(curr.toCharArray(), adj, index);
                    if (!visited.contains(newBoard)) {
                        q.offer(newBoard);
                        visited.add(newBoard);
                    }
                }
            }
            step++;
        }
        return -1;
    }

    private String swap(char[] chars, int i, int j) {
        char temp = chars[i];
        chars[i] = chars[j];
        chars[j] = temp;
        return new String(chars);
    }
}
