package cx.leetcode.O;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Solution241 {
    /**
     * 一个由数字和运算符组成的字符串 expression ，按不同优先级组合数字和运算符，计算并返回所有可能组合的结果
     * 就是给算式加括号，所有的可能都加一次
     * 分治算法
     */
    Map<String, List<Integer>> memo = new HashMap<>();

    public List<Integer> diffWaysToCompute(String expression) {
        if (memo.containsKey(expression)) {
            return memo.get(expression);
        }
        List<Integer> res = new ArrayList<>();
        for (int i = 0; i < expression.length(); i++) {
            char c = expression.charAt(i);
            if (c == '+' || c == '-' || c == '*') {
                //分
                List<Integer> left = diffWaysToCompute(expression.substring(0, i));
                List<Integer> right = diffWaysToCompute(expression.substring(i + 1));
                //治
                for (int a : left) {
                    for (int b : right) {
                        if (c == '+') {
                            res.add(a + b);
                        } else if (c == '-') {
                            res.add(a - b);
                        } else {
                            res.add(a * b);
                        }
                    }
                }
            }
        }
        //base case 没有运算符，只有数字
        if (res.isEmpty()) {
            res.add(Integer.parseInt(expression));
        }
        memo.put(expression, res);
        return res;
    }
}
