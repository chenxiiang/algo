/*
 * Copyright (c) Huawei Technologies Co., Ltd. 2023-2023. All rights reserved.
 */

package cx.leetcode.daily;

import java.util.Arrays;

/**
 * 2502. 设计内存分配器
 *
 * @author c00575945
 * @since 2023-01-10
 */
public class Allocator {
    int total;

    int[] map;

    public Allocator(int n) {
        total = n;
        map = new int[n];
    }

    public int allocate(int size, int mID) {
        int used = 0;
        for (int i = 0; i < total; i++) {
            if (map[i] == 0) {
                used++;
            } else {
                used = 0;
            }
            if (used == size) {
                Arrays.fill(map, i + 1 - size, i + 1, mID);
                return i + 1 - size;
            }
        }
        return -1;
    }

    public int free(int mID) {
        int removed = 0;
        for (int i = 0; i < total; i++) {
            if (map[i] == mID) {
                removed++;
                map[i] = 0;
            }
        }
        return removed;
    }
}
