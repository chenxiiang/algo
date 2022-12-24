/*
 * Copyright (c) Huawei Technologies Co., Ltd. 2022-2022. All rights reserved.
 */

package cx.leetcode.daily;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * 439. 三元表达式解析器
 *
 * @author c00575945
 * @since 2022-12-24
 */
public class Solution439 {
    public String parseTernary(String expression) {
        if (expression == null || expression.length() < 5) {
            return "";
        }
        Deque<Character> stack = new ArrayDeque<>();
        int n = expression.length();
        for (int i = n - 1; i >= 0; i--) {
            char curr = expression.charAt(i);
            if (!stack.isEmpty() && stack.peek() == '?') {
                stack.pop();
                char first = stack.pop();
                stack.pop();
                char second = stack.pop();
                if (curr == 'T') {
                    stack.push(first);
                } else {
                    stack.push(second);
                }
            } else {
                stack.push(curr);
            }
        }
        return String.valueOf(stack.pop());
    }
}
