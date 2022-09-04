/*
 * Copyright (c) Huawei Technologies Co., Ltd. 2022-2022. All rights reserved.
 */

package cx.leetcode.array.slidewindow;

import java.util.HashMap;
import java.util.Map;

/**
 * 功能描述
 *
 * @author c00575945
 * @since 2022-09-01
 */
public class Offer014 {
    public boolean checkInclusion(String s1, String s2) {
        Map<Character, Integer> window = new HashMap<>(), need = new HashMap<>();
        int left = 0, right = 0, valid = 0;
        for (char c : s1.toCharArray()) {
            need.put(c, need.getOrDefault(c, 0) + 1);
        }
        while (right < s2.length()) {
            Character c = s2.charAt(right);
            right++;
            if (need.containsKey(c)) {
                window.put(c, window.getOrDefault(c, 0) + 1);
                if (window.get(c).equals(need.get(c))) {
                    valid++;
                }
            }
            if (right - left >= s1.length()) {
                if (valid == need.size()) {
                    return true;
                }
                Character d = s2.charAt(left);
                left++;
                if (need.containsKey(d)) {
                    if (need.get(d).equals(window.get(d))) {
                        valid--;
                    }
                    window.put(d, window.get(d) - 1);
                }
            }
        }
        return false;
    }
}
