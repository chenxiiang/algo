/*
 * Copyright (c) Huawei Technologies Co., Ltd. 2022-2022. All rights reserved.
 */

package cx.leetcode.daily;

/**
 * 1754. 构造字典序最大的合并字符串
 *
 * @author c00575945
 * @since 2022-12-24
 */
public class Solution1754 {
    public String largestMerge(String word1, String word2) {
        int m = word1.length(), n = word2.length();
        int i = 0, j = 0;
        StringBuilder ans = new StringBuilder();
        while (i < m && j < n) {
            boolean gt = word1.substring(i).compareTo(word2.substring(j)) > 0;
            ans.append(gt ? word1.charAt(i++) : word2.charAt(j++));
        }
        ans.append(word1.substring(i));
        ans.append(word2.substring(j));
        return ans.toString();
    }
}
