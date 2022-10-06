package cx.leetcode.tanxin;

import java.util.Arrays;
import java.util.Comparator;

public class Solution452 {
    public int findMinArrowShots(int[][] points) {
        if (points.length == 0) {
            return 0;
        }
        int count = 1;
        Arrays.sort(points, Comparator.comparingInt(a -> a[1]));
        int x_end = points[0][1];
        for (int[] point : points) {
            int start = point[0];
            if (start > x_end) {
                count++;
                x_end = point[1];
            }
        }
        return count;
    }
}
