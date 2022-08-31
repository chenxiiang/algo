package cx.leetcode.array;

public class Solution59 {
    public int[][] generateMatrix(int n) {
        int[][] res = new int[n][n];
        int num = 1;
        int left = 0, right = n - 1, upper = 0, lower = n - 1;
        while (num <= n * n) {
            if (upper <= lower) {
                for (int i = left; i <= right; i++) {
                    res[upper][i] = num++;
                }
                upper++;
            }

            if (right >= left) {
                for (int i = upper; i <= lower; i++) {
                    res[i][right] = num++;
                }
                right--;
            }

            if (lower >= upper) {
                for (int i = right; i >= left; i--) {
                    res[lower][i] = num++;
                }
                lower--;
            }

            if (left <= right) {
                for (int i = lower; i >= upper; i--) {
                    res[i][left] = num++;
                }
                left++;
            }
        }
        return res;
    }
}
