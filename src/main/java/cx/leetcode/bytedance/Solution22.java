package cx.leetcode.bytedance;

import java.util.ArrayList;
import java.util.List;

public class Solution22 {
    List<String> res;

    public List<String> generateParenthesis(int n) {
        res = new ArrayList<>();
        if (n == 0) {
            return res;
        }
        //n是括号的对数，也就是左右各n个
        backtrack(new StringBuilder(), n, n);
        return res;
    }

    //可用的左括号为left个，可用的右括号为right个
    void backtrack(StringBuilder sb, int left, int right) {
        if (right < left) {
            return;
        }
        if (left < 0 || right < 0) {
            return;
        }
        //左右括号都已用完
        if (left == 0 && right == 0) {
            res.add(sb.toString());
            return;
        }
        //每个位置都有左右括号两种选择
        sb.append("(");
        backtrack(sb, left - 1, right);
        sb.deleteCharAt(sb.length() - 1);

        sb.append(")");
        backtrack(sb, left, right - 1);
        sb.deleteCharAt(sb.length() - 1);
    }
}
