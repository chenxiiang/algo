/*
 * Copyright (c) Huawei Technologies Co., Ltd. 2022-2022. All rights reserved.
 */

package cx.leetcode.daily;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.TreeMap;

/**
 * 功能描述
 *
 * @author c00575945
 * @since 2022-12-23
 */
public class FileSharing {
    private TreeMap<Integer, Set<Integer>> map;

    private int chunks;

    public FileSharing(int m) {
        map = new TreeMap<>();
        chunks = m;
    }

    public int join(List<Integer> ownedChunks) {
        int userId = 0;
        for (int i = 1; i < Integer.MAX_VALUE; i++) {
            if (!map.containsKey(i)) {
                userId = i;
                break;
            }
        }
        map.put(userId, new HashSet<>(ownedChunks));
        return userId;
    }

    public void leave(int userID) {
        map.remove(userID);
    }

    public List<Integer> request(int userID, int chunkID) {
        List<Integer> ret = new ArrayList<>();
        if (chunkID < 1 || chunkID > chunks) {
            return ret;
        }
        map.entrySet()
            .stream()
            .filter(entry -> entry.getValue().contains(chunkID))
            .forEach(entry -> ret.add(entry.getKey()));
        if (!ret.isEmpty()) {
            map.get(userID).add(chunkID);
        }
        return ret;
    }
}
