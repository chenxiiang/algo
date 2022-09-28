package cx.leetcode.tanxin;

import java.util.Arrays;
import java.util.Comparator;

public class Solution435 {
    /**
     * 感觉这个题按照生活习惯很容易按照结束时间早的来做，前面的越早结束后面的等待时间就越少
     * 按照右边界排序，就要从左向右遍历，因为右边界越小越好，只要右边界越小，留给下一个区间的空间就越大，所以从左向右遍历，优先选右边界小的。
     * 按照左边界排序，就要从右向左遍历，因为左边界数值越大越好（越靠右），这样就给前一个区间的空间就越大，所以可以从右向左遍历。
     */
    public int eraseOverlapIntervals(int[][] intervals) {
        if (intervals.length == 0) {
            return 0;
        }
        //按照结束时间升序排列
        Arrays.sort(intervals, Comparator.comparingInt(a -> a[1]));
        //至少有一个区间不相交
        int count = 1;
        //排序后第一个区间就是x
        int x_end = intervals[0][1];
        for (int[] interval : intervals) {
            int start = interval[0];
            if (start >= x_end) {
                count++;
                x_end = interval[1];
            }
        }
        return intervals.length - count;
    }
}
