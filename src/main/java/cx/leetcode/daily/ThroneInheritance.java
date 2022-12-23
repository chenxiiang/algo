/*
 * Copyright (c) Huawei Technologies Co., Ltd. 2022-2022. All rights reserved.
 */

package cx.leetcode.daily;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 1600 王位继承顺序
 *
 * @author c00575945
 * @since 2022-12-23
 */
public class ThroneInheritance {
    String kingName;

    Map<String, List<String>> map = new HashMap<>();

    Set<String> deathSet = new HashSet<>();

    public ThroneInheritance(String kingName) {
        this.kingName = kingName;
        map.put(kingName, new LinkedList<>());
    }

    public void birth(String parentName, String childName) {
        map.get(parentName).add(childName);
        map.put(childName, new LinkedList<>());
    }

    public void death(String name) {
        deathSet.add(name);
    }

    public List<String> getInheritanceOrder() {
        List<String> result = new LinkedList<>();
        dfs(result, kingName);
        return result;
    }

    private void dfs(List<String> result, String name) {
        if (!deathSet.contains(name)) {
            result.add(name);
        }
        for (String subName : map.get(name)) {
            dfs(result, subName);
        }
    }
}
