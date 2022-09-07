package cx.leetcode.array;

import java.util.*;

public class Solution710 {

    int bound;

    Map<Integer, Integer> map;

    Random random;

    Set<Integer> black;

    public Solution710(int n, int[] blacklist) {
        bound = n - blacklist.length;
        map = new HashMap<>();
        random = new Random();
        black = new HashSet<>();
        for (int b : blacklist) {
            if (b >= this.bound) {
                black.add(b);
            }
        }
        int w = this.bound;
        //这里把两个循环分开，是因为必须先把bound之后的黑名单元素加到black set中，如果写在一个循环中，可能会造成把实际的黑名单识别成白名单，因为还没遍历到，没有加到set中
        for (int b : blacklist) {
            if (b < bound) {
                while (black.contains(w)) {
                    w++;
                }
                //把bound之前的黑名单映射到w位置上
                map.put(b, w++);
            }
        }

    }

    public int pick() {
        int rand = random.nextInt(bound);
        return map.getOrDefault(rand, rand);
    }
}
