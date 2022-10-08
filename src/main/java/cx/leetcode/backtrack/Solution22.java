package cx.leetcode.backtrack;

import java.util.ArrayList;
import java.util.List;

/**
 * 1、一个「合法」括号组合的左括号数量一定等于右括号数量，这个很好理解。
 * <p>
 * 2、对于一个「合法」的括号字符串组合 p，必然对于任何 0 <= i < len(p) 都有：子串 p[0..i] 中左括号的数量都大于或等于右括号的数量。
 *
 * 框架如下
 * void backtrack(int n, int i, string& track) {
 *     // i 代表当前的位置，共 2n 个位置
 *     // 穷举到最后一个位置了，得到一个长度为 2n 组合
 *     if (i == 2 * n) {
 *         print(track);
 *         return;
 *     }
 *
 *     // 对于每个位置可以是左括号或者右括号两种选择
 *     for choice in ['(', ')'] {
 *         track.push(choice); // 做选择
 *         // 穷举下一个位置
 *         backtrack(n, i + 1, track);
 *         track.pop(choice); // 撤销选择
 *     }
 * }
 */
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
            //说明已有的序列中left比right少，则已经不合法了
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
