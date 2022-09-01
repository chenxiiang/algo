package cx.leetcode.slidewindow;

import java.util.HashMap;
import java.util.Map;

public class Solution567 {
    public boolean checkInclusion(String s1, String s2) {
        Map<Character, Integer> window = new HashMap<>();
        Map<Character, Integer> need = new HashMap<>();
        int left = 0, right = 0, valid = 0;
        for (char c : s1.toCharArray()) {
            need.put(c, need.getOrDefault(c, 0) + 1);
        }
        while (right < s2.length()) {
            Character c = s2.charAt(right);
            right++;
            if (need.containsKey(c)) {
                //窗口的更新放在if外面也可以，但是更新的是无用的字符信息
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
                    if (window.get(d).equals(need.get(d))) {
                        valid--;
                    }
                    window.put(d, window.get(d) - 1);
                }
            }
        }
        return false;
    }
}
