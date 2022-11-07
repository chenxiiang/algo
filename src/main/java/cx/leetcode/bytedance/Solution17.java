package cx.leetcode.bytedance;

import java.util.ArrayList;
import java.util.List;

public class Solution17 {
    public List<String> letterCombinations(String digits) {
        List<String> res = new ArrayList<>();
        if (digits == null || digits.length() == 0) {
            return res;
        }
        String[] lettersMap = {"", "", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};
        res.add("");
        for (int i = 0; i < digits.length(); i++) {
            String letter = lettersMap[digits.charAt(i) - '0'];
            int size = res.size();
            for (int j = 0; j < size; j++) {
                String temp = res.remove(0);
                for (int k = 0; k < letter.length(); k++) {
                    res.add(temp + letter.charAt(k));
                }
            }
        }
        return res;
    }
}
