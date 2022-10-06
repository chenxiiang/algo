package cx.leetcode.tanxin;

import java.util.Arrays;

public class Solution253 {
    //扫描线方法，遇到开始的点就+1，遇到结束的点就-1，记录中间过程的最大值
    public int minMeetingRooms(int[][] intervals) {
        int n = intervals.length;
        int[] begin = new int[n];
        int[] end = new int[n];
        for (int i = 0; i < n; i++) {
            begin[i] = intervals[i][0];
            end[i] = intervals[i][1];
        }
        Arrays.sort(begin);
        Arrays.sort(end);
        int count = 0;
        int res = 0, i = 0, j = 0;
        while (i < n && j < n) {
            if (begin[i] < end[j]) {
                count++;
                i++;
            } else {
                count--;
                j++;
            }
            res = Math.max(res, count);
        }
        return res;
    }
}
