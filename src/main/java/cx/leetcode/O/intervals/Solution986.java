package cx.leetcode.O.intervals;

import java.util.ArrayList;
import java.util.List;

public class Solution986 {
    public int[][] intervalIntersection(int[][] firstList, int[][] secondList) {
        int i = 0, j = 0;
        List<int[]> res = new ArrayList<>();
        while (i < firstList.length && j < secondList.length) {
            int left1 = firstList[i][0], right1 = firstList[i][1];
            int left2 = secondList[j][0], right2 = secondList[j][1];
            //不存在交集的情况 left1>right2 或 left2>right1，其余的就是有交集
            if (right2 >= left1 && right1 >= left2) {
                //这种情况可以完全覆盖各种交集以及包含关系，无论哪个包含哪个，无论左交集还是右交集都是一样的情况
                res.add(new int[]{Math.max(left1, left2), Math.min(right1, right2)});
            }
            if (right2 < right1) {
                j++;
            } else {
                i++;
            }
        }
        return res.toArray(new int[0][]);
    }
}
