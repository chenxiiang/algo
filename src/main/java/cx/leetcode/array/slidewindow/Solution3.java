package cx.leetcode.array.slidewindow;

import java.util.HashMap;
import java.util.Map;

public class Solution3 {
    public int lengthOfLongestSubstring(String s) {
        if (s.length() == 0) {
            return 0;
        }
        Map<Character, Integer> window = new HashMap<>();
        int left = 0, right = 0;
        int res = 0;
        while (right < s.length()) {
            char c = s.charAt(right);
            right++;
            window.put(c, window.getOrDefault(c, 0) + 1);
            while (window.get(c) > 1) {
                char d = s.charAt(left);
                left++;
                window.put(d, window.getOrDefault(d, 1) - 1);
            }
            res = Math.max(res, right - left);
        }
        return res;
    }

}
