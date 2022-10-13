package cx.leetcode.O.binarysearch;

import java.util.ArrayList;
import java.util.List;

public class Solution392 {
    //判断s是否为t的子序列
    public boolean isSubsequence(String s, String t) {
        //最容易想到的是这种方法，双指针，一边前进一边比较，但是复杂度是On,n是t的长度
        int i = 0, j = 0;
        while (i < s.length() && j < t.length()) {
            if (s.charAt(i) == t.charAt(j)) {
                i++;
            }
            j++;
        }
        return i == s.length();
    }
}
