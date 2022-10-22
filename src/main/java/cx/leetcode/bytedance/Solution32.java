package cx.leetcode.bytedance;

import java.util.ArrayDeque;
import java.util.Deque;

public class Solution32 {
    public int longestValidParentheses(String s) {
        int max = 0;
        Deque<Integer> stack = new ArrayDeque<>();
        int start = 0;//记录当前串的起始位置
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                stack.push(i);
            } else {
                if (stack.isEmpty()) {
                    //说明当前的有括号没有匹配的左括号，起始位置从下一个开始
                    start = i + 1;
                } else {
                    //右括号不入栈，所以弹出来的都是左括号
                    stack.pop();
                    if (stack.isEmpty()) {
                        //所有的左括号都匹配完了
                        max = Math.max(max, i - start + 1);
                    } else {
                        //当前的左括号匹配完还有其他的左括号
                        max = Math.max(max, i - stack.peek());
                    }
                }
            }
        }
        return max;
    }

    //还有一种双·双变量左右遍历的方法不需要额外空间
    public int longestValidParentheses1(String s) {
        //记录遇到的左右括号的数量
        int left = 0, right = 0, max = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                left++;
            } else {
                right++;
            }
            if (left == right) {
                max = Math.max(max, 2 * right);
            } else if (right > left) {
                left = right = 0;
            }
        }
//如果遍历过程中左括号一直比右括号多，则单向遍历得不到结果，比如(()得到的是0
        left = right = 0;
        for (int i = s.length() - 1; i >= 0; i--) {
            if (s.charAt(i) == '(') {
                left++;
            } else {
                right++;
            }
            if (left == right) {
                max = Math.max(max, 2 * left);
            } else if (left > right) {
                left = right = 0;
            }
        }
        return max;
    }
}
