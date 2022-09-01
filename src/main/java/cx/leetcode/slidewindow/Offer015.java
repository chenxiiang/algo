package cx.leetcode.slidewindow;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Offer015 {
    public List<Integer> findAnagrams(String s, String p) {
        Map<Character, Integer> window = new HashMap<>(), need = new HashMap<>();
        List<Integer> res = new ArrayList<>();
        int left = 0, right = 0, valid = 0;
        for (char c : p.toCharArray()) {
            need.put(c, need.getOrDefault(c, 0) + 1);
        }
        while (right < s.length()) {
            Character c = s.charAt(right);
            right++;
            if (need.containsKey(c)) {
                window.put(c, window.getOrDefault(c, 0) + 1);
                if (window.get(c).equals(need.get(c))) {
                    valid++;
                }
            }
            while (right - left >= p.length()) {
                if (valid == need.size()) {
                    res.add(left);
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
        return res;
    }
}
