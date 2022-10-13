package cx.leetcode.O;

import java.util.HashSet;
import java.util.Set;

public class Solution391 {
    /**
     * 从顶点和面积的角度来做
     * 所有矩形坐标中最小的左下角和最大的右上角就是完美矩形的左下和右上（如果存在的话），然后所有小矩形的总面积与完美矩形的面积不相等，肯定是false，因为原定有空缺或重叠
     * 但是面积相等并不一定是完美矩形，因为拼在一起的图形可能就不是个矩形，然后从顶点的角度来看
     * <p>
     * 然后从顶点的角度来看是不是个矩形
     * 当某一个点同时是 2 个或者 4 个小矩形的顶点时，该点最终不是顶点；当某一个点同时是 1 个或者 3 个小矩形的顶点时，该点最终是一个顶点。
     * 如果某一个顶点 p 存在于集合 points 中，则将它删除；如果不存在于集合 points 中，则将它插入。这个简单的逻辑，让 points 集合最终只会留下那些出现了 1 次或者 3 次的顶点，那些出现了 2 次或者 4 次的顶点都被消掉了。
     * points 集合中最后应该只有 4 个顶点对吧，如果 len(points) != 4 说明最终构成的图形肯定不是完美矩形。
     */
    public boolean isRectangleCover(int[][] rectangles) {
        //假如存在完美矩形，它的左下角坐标
        int x1_expect = Integer.MAX_VALUE, y1_expect = Integer.MAX_VALUE;
        //右上角坐标
        int x2_expect = Integer.MIN_VALUE, y2_expect = Integer.MIN_VALUE;
        Set<String> points = new HashSet<>();
        int actualArea = 0;
        for (int[] item : rectangles) {
            int x1 = item[0], y1 = item[1], x2 = item[2], y2 = item[3];
            x1_expect = Math.min(x1_expect, x1);
            y1_expect = Math.min(y1_expect, y1);
            x2_expect = Math.max(x2_expect, x2);
            y2_expect = Math.max(y2_expect, y2);
            //把各个小矩形的面积相加
            actualArea += (x2 - x1) * (y2 - y1);
            //构造小矩形的四个顶点然后加入points
            int[] p1 = new int[]{x1, y1};
            int[] p2 = new int[]{x1, y2};
            int[] p3 = new int[]{x2, y1};
            int[] p4 = new int[]{x2, y2};
            for (int[] p : new int[][]{p1, p2, p3, p4}) {
                String s = p[0] + "," + p[1];
                if (points.contains(s)) {
                    points.remove(s);
                } else {
                    points.add(s);
                }
            }
        }
        int expectArea = (x2_expect - x1_expect) * (y2_expect - y1_expect);
        if (actualArea != expectArea) {
            return false;
        }
        if (points.size() != 4) {
            return false;
        }
        String s1 = x1_expect + "," + y1_expect;
        String s2 = x1_expect + "," + y2_expect;
        String s3 = x2_expect + "," + y1_expect;
        String s4 = x2_expect + "," + y2_expect;
        if (!points.contains(s1)) {
            return false;
        }
        if (!points.contains(s2)) {
            return false;
        }
        if (!points.contains(s3)) {
            return false;
        }
        if (!points.contains(s4)) {
            return false;
        }
        return true;
    }
}
