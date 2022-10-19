package cx.leetcode.bytedance;

import java.util.ArrayDeque;
import java.util.Deque;

public class Solution20 {
    /**
     * 判断括号串是否合法
     * 因为由三种括号，必须每对括号都匹配后才算匹配，不能交叉出现，这样的话即使每种括号各自匹配，总体也是不匹配的
     * 比如[(])，就是false
     * 所以要用栈来为右括号匹配最近的左括号
     */
    public boolean isValid(String s) {
        Deque<Character> left = new ArrayDeque<>();
        for (char c : s.toCharArray()) {
            if (c == '(' || c == '[' || c == '{') {
                left.push(c);
            } else {
                if (!left.isEmpty() && leftOf(c) == left.peek()) {
                    left.pop();
                } else {
                    return false;
                }
            }
        }
        //如果全部弹出了，就是都匹配了
        return left.isEmpty();
    }

    private char leftOf(char c) {
        switch (c) {
            case ')':
                return '(';
            case ']':
                return '[';
            default:
                return '{';
        }
    }
}
