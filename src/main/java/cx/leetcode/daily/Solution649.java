/*
 * Copyright (c) Huawei Technologies Co., Ltd. 2022-2022. All rights reserved.
 */

package cx.leetcode.daily;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 649. Dota2 参议院
 *
 * @author c00575945
 * @since 2022-12-24
 */
public class Solution649 {
    /**
     * 使用两个队列分别保存R阵营和D阵营的参议员索引，
     * 在每一轮循环中，比较相邻两个R和D阵营的参议员的索引，
     * 保留索引小（min）的，并将该(min + senate.length)添加到该阵营队列尾部
     * 去除索引大的，即不添加到末尾。
     */
    public String predictPartyVictory(String senate) {
        Queue<Integer> radiant = new LinkedList<>();
        Queue<Integer> dire = new LinkedList<>();
        int n = senate.length();
        char c;
        for (int i = 0; i < n; i++) {
            c = senate.charAt(i);
            if (c == 'R') {
                radiant.offer(i);
            } else {
                dire.offer(i);
            }
        }
        while (!radiant.isEmpty() && !dire.isEmpty()) {
            int r = radiant.poll();
            int d = dire.poll();
            if (r < d) {
                radiant.offer(r + n);
            } else {
                dire.offer(d + n);
            }
        }
        return !radiant.isEmpty() ? "Radiant" : "Dire";
    }
}
