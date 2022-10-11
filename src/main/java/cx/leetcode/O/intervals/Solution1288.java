package cx.leetcode.O.intervals;

import java.util.Arrays;

public class Solution1288 {
    /**
     * 求出被覆盖区间的数量，相减即可
     * 排序 画图
     */
    public int removeCoveredIntervals(int[][] intervals) {
        Arrays.sort(intervals, (a, b) -> {
            if (a[0] == b[0]) {
                return b[1] - a[1];
            }
            return a[0] - b[0];
        });
        int left = intervals[0][0];
        int right = intervals[0][1];
        int res = 0;
        for (int i = 1; i < intervals.length; i++) {
            int[] interval = intervals[i];
            if (left <= interval[0] && right >= interval[1]) {
                res++;
            } else if (right >= interval[0] && right <= interval[1]) {
                right = interval[1];
            } else if (right < interval[0]) {
                left = interval[0];
                right = interval[1];
            }
        }
        return intervals.length - res;
    }
}
