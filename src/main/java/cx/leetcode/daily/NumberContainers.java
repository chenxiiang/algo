/*
 * Copyright (c) Huawei Technologies Co., Ltd. 2023-2023. All rights reserved.
 */

package cx.leetcode.daily;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * 2349. 设计数字容器系统
 *
 * @author c00575945
 * @since 2023-01-10
 */
public class NumberContainers {
    Map<Integer, Integer> map;

    Map<Integer, PriorityQueue<Integer>> check;

    public NumberContainers() {
        map = new HashMap<>();
        check = new HashMap<>();
    }

    public void change(int index, int number) {
        map.put(index, number);
        if (!check.containsKey(number)) {
            check.put(number, new PriorityQueue<>());
        }
        check.get(number).offer(index);
    }

    public int find(int number) {
        if (!check.containsKey(number)) {
            return -1;
        }
        PriorityQueue<Integer> pq = check.get(number);
        while (!pq.isEmpty()) {
            int p = pq.peek();
            if (map.get(p) == number) {
                return p;
            } else {
                pq.poll();
            }
        }
        return -1;
    }
}
