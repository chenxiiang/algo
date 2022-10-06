package cx.leetcode.tanxin;

import java.util.Arrays;

public class Solution1024 {
    public int videoStitching(int[][] clips, int time) {
        if (time == 0) {
            return 0;
        }
        //按照起点升序，终点降序的顺序排列
        Arrays.sort(clips, (a, b) -> {
            if (a[0] == b[0]) {
                return b[1] - a[1];
            }
            return a[0] - b[0];
        });

        int res = 0;
        int curEnd = 0, nextEnd = 0;
        int i = 0, n = clips.length;
        while (i < n && clips[i][0] <= curEnd) {
            //在第res个视频的区间内贪心选择下一个视频
            while (i < n && clips[i][0] <= curEnd) {
                nextEnd = Math.max(nextEnd, clips[i][1]);
                i++;
            }
            res++;
            curEnd = nextEnd;
            if (curEnd >= time) {
                return res;
            }
        }
        return -1;
    }
}
