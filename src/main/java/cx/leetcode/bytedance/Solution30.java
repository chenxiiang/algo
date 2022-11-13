package cx.leetcode.bytedance;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Solution30 {
    public List<Integer> findSubstring(String s, String[] words) {
        List<Integer> res = new ArrayList<>();
        int wordNum = words.length;
        if (wordNum == 0) {
            return res;
        }

        int wordLen = words[0].length();
        //记录数组中每个单词的数量
        Map<String, Integer> allWords = new HashMap<>();
        for (String w : words) {
            int val = allWords.getOrDefault(w, 0);
            allWords.put(w, val + 1);
        }
        for (int j = 0; j < wordLen; j++) {
            Map<String, Integer> hasWords = new HashMap<>();
            int num = 0;//记录hasWords中有多少个单词
            for (int i = j; i < s.length() - wordNum * wordLen + 1; i += wordLen) {
                boolean hasRemoved = false;
                while (num < wordNum) {
                    String word = s.substring(i + num * wordLen, i + (num + 1) * wordLen);
                    if (allWords.containsKey(word)) {
                        int value = hasWords.getOrDefault(word, 0);
                        hasWords.put(word, value + 1);
                        if (hasWords.get(word) > allWords.get(word)) {
                            //遇到合适的单词，但是次数超了
                            hasRemoved = true;
                            int removeNum = 0;
                            //一直移除单词，直到次数符合
                            while (hasWords.get(word) > allWords.get(word)) {
                                String firstWord = s.substring(i + removeNum * wordLen, i + (removeNum + 1) * wordLen);
                                int v = hasWords.get(firstWord);
                                hasWords.put(firstWord, v - 1);
                                removeNum++;
                            }
                            num = num - removeNum + 1;      //加 1 是因为我们把当前单词加入到了hasWords中
                            i = i + (removeNum - 1) * wordLen;
                            break;
                        }
                    } else {
                        //遇到了不匹配的单词，直接将 i 移动到该单词的后边（但其实这里
                        //只是移动到了出现问题单词的地方，因为最外层有 for 循环， i 还会移动一个单词
                        //然后刚好就移动到了单词后边）
                        hasWords.clear();
                        i = i + num * wordLen;
                        num = 0;
                        break;
                    }
                    num++;
                }
                if (num == wordNum) {
                    res.add(i);
                }

                //子串完全匹配，将上一个子串的第一个单词从hasWords中移除
                if (num > 0 && !hasRemoved) {
                    String firstWord = s.substring(i, i + wordLen);
                    int v = hasWords.get(firstWord);
                    hasWords.put(firstWord, v - 1);
                    num--;
                }
            }
        }
        return res;
    }
}
