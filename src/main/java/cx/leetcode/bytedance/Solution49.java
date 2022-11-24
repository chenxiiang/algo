package cx.leetcode.bytedance;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Solution49 {
    public List<List<String>> groupAnagrams(String[] strs) {
        Map<String, List<String>> codeToGroup = new HashMap<>();
        for (String s : strs) {
            String code = encode(s);
            //把编码相同的单词放一起
            codeToGroup.putIfAbsent(code, new ArrayList<>());
            codeToGroup.get(code).add(s);
        }

        return new ArrayList<>(codeToGroup.values());
    }

    private String encode(String s) {
        //用char数组主要是因为int数组不能创建字符串
        char[] count = new char[26];
        for (char c : s.toCharArray()) {
            int delta = c - 'a';
            count[delta]++;
        }
        return new String(count);
    }
}
