package cx.leetcode.O.intervals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * 合并区间
 */
public class Solution56 {
    public int[][] merge(int[][] intervals) {
        Arrays.sort(intervals, Comparator.comparingInt(a -> a[0]));
        int left = intervals[0][0];
        int right = intervals[0][1];
        List<int[]> res = new ArrayList<>();
        for (int i = 1; i < intervals.length; i++) {
            int[] interval = intervals[i];
            if (interval[0] > right) {
                res.add(new int[] {left, right});
                left = interval[0];
                right = interval[1];
            } else if (interval[0] >= left) {
                right = Math.max(right, interval[1]);
            }
        }
        res.add(new int[] {left, right});
        return res.toArray(new int[0][]);
    }
}
