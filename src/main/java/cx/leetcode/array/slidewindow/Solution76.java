package cx.leetcode.array.slidewindow;

import java.util.HashMap;
import java.util.Map;

public class Solution76 {
    public String minWindow(String s, String t) {
        Map<Character, Integer> window = new HashMap<>(), need = new HashMap<>();
        int left = 0, right = 0, valid = 0;
        int start = 0, len = Integer.MAX_VALUE;
        for (char c : t.toCharArray()) {
            need.put(c, need.getOrDefault(c, 0) + 1);
        }
        while (right < s.length()) {
            Character c = s.charAt(right);
            right++;
            if (need.containsKey(c)) {
                window.put(c, window.getOrDefault(c, 0) + 1);
                if (need.get(c).equals(window.get(c))) {
                    valid++;
                }
            }
            while (valid == need.size()) {
                if (right - left < len) {
                    start = left;
                    len = right - left;
                }
                Character d = s.charAt(left);
                left++;
                if (need.containsKey(d)) {
                    if (window.get(d).equals(need.get(d))) {
                        valid--;
                    }
                    window.put(d, window.get(d) - 1);
                }
            }
        }
        return len == Integer.MAX_VALUE ? "" : s.substring(start, start + len);
    }
}
