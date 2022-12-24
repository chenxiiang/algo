/*
 * Copyright (c) Huawei Technologies Co., Ltd. 2022-2022. All rights reserved.
 */

package cx.leetcode.daily;

import java.util.HashMap;
import java.util.Map;

/**
 * 功能描述
 *
 * @author c00575945
 * @since 2022-12-24
 */
public class Solution383 {
    public boolean canConstruct(String ransomNote, String magazine) {
        // if (magazine.contains(ransomNote)) {
        //     return true;
        // }
        // Map<Character, Integer> map = new HashMap<>();
        // for (char c : magazine.toCharArray()) {
        //     map.put(c, map.getOrDefault(c, 0) + 1);
        // }
        // for (char c : ransomNote.toCharArray()) {
        //     if (!map.containsKey(c)) {
        //         return false;
        //     }
        //     int cnt = map.get(c);
        //     if (cnt <= 0) {
        //         return false;
        //     }
        //     map.put(c, cnt - 1);
        // }
        // return true;

        int[] arr = new int[26];
        int temp;
        for (int i = 0; i < magazine.length(); i++) {
            temp = magazine.charAt(i) - 'a';
            arr[temp]++;
        }
        for (int i = 0; i < ransomNote.length(); i++) {
            temp = ransomNote.charAt(i) - 'a';
            if (arr[temp] > 0) {
                arr[temp]--;
            } else {
                return false;
            }
        }
        return true;
    }
}
