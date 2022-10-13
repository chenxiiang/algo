package cx.leetcode.O;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeSet;

public class Solution855 {
    // 将端点 p 映射到以 p 为左端点的线段
    private Map<Integer, int[]> startMap;
    //将端点q映射到以q为右端点的线段
    private Map<Integer, int[]> endMap;

    //根据线段长度从小到大存放所有线段
    private TreeSet<int[]> pq;
    private int n;

    public Solution855(int n) {
        this.n = n;
        startMap = new HashMap<>();
        endMap = new HashMap<>();
        pq = new TreeSet<>((a, b) -> {
            int distA = distance(a);
            int distB = distance(b);
            if (distA == distB) {
                //长度相同就比较索引，把索引较小的那个放到长度较大的那一头，也就是说，先去长度最短的那个，如果长度相等，就取索引最小的那个
                return b[0] - a[0];
            }
            return distA - distB;
        });
        addInterval(new int[]{-1, n});
    }

    /* 去除一个线段 */
    private void removeInterval(int[] intv) {
        pq.remove(intv);
        startMap.remove(intv[0]);
        endMap.remove(intv[1]);
    }

    /* 增加一个线段 */
    private void addInterval(int[] intv) {
        pq.add(intv);
        startMap.put(intv[0], intv);
        endMap.put(intv[1], intv);
    }

    /* 计算一个线段的长度 */
    private int distance(int[] intv) {
        //4和2中间只有3一个位置，所以还要-1
        //这种方法算的是线段两个端点间的长度
        //return intv[1] - intv[0] - 1;

        //为了在有多个选择时返回较小的索引，所以不能让这里直接计算两个端点间的长度，而要让它计算该线段中点与端点之间的长度
        int x = intv[0];
        int y = intv[1];
        if (x == -1) {
            return y;
        }
        if (y == n) {
            return n - x - 1;
        }
        return (y - x) / 2;
    }

    public int seat() {
        //从有序集合中拿出最长线段
        int[] longest = pq.last();
        int x = longest[0];
        int y = longest[1];
        int seat;
        if (x == -1) {
            seat = 0;     // 情况1
        } else if (y == n) {
            seat = n - 1;
        } else {
            seat = (y - x) / 2 + x;
        }

        //将最长的线段分为两段
        int[] left = new int[]{x, seat};
        int[] right = new int[]{seat, y};
        removeInterval(longest);
        addInterval(left);
        addInterval(right);
        return seat;
    }

    public void leave(int p) {
        //将p左右的线段找出来
        int[] right = startMap.get(p);
        int[] left = endMap.get(p);
        //将两个线段合一
        int[] merged = new int[]{left[0], right[1]};
        removeInterval(left);
        removeInterval(right);
        addInterval(merged);
    }
}
