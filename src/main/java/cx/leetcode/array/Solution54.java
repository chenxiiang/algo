package cx.leetcode.array;

import java.util.ArrayList;
import java.util.List;

public class Solution54 {
    public List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> res = new ArrayList<>();
        int row = matrix.length, col = matrix[0].length;
        int left = 0, right = col - 1, upper = 0, lower = row - 1;
        while (res.size() < col * row) {
            if (upper <= lower) {
                for (int i = left; i <= right; i++) {
                    res.add(matrix[upper][i]);
                }
                upper++;
            }

            if (right >= left) {
                for (int i = upper; i <= lower; i++) {
                    res.add(matrix[i][right]);
                }
                right--;
            }

            if (lower >= upper) {
                for (int i = right; i >= left; i--) {
                    res.add(matrix[lower][i]);
                }
                lower--;
            }

            if (left <= right) {
                for (int i = lower; i >= upper; i--) {
                    res.add(matrix[i][left]);
                }
                left++;
            }
        }
        return res;
    }
}
