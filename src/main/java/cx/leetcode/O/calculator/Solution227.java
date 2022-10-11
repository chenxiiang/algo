package cx.leetcode.O.calculator;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * 就是在224的基础上没有括号，直接把括号的提交上去也能过
 */
public class Solution227 {
    public int calculate(String s) {
        Deque<Integer> stack = new ArrayDeque<>();
        //记录算式中的数字
        int num = 0;
        //记录num前的符号，初始化为+
        char sign = '+';
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (Character.isDigit(c)) {
                num = 10 * num + (c - '0');
            }
            //如果不是数字，那就是遇到了下一个符号，就把前面的数字和符号都压栈，最后一个数字也压栈
            //因为存在空格，所以加个判断
            if ((!Character.isDigit(c) && c != ' ') || i == s.length() - 1) {
                int pre;
                switch (sign) {
                    case '+':
                        stack.push(num);
                        break;
                    case '-':
                        stack.push(-num);
                        break;
                    //乘除法优先于加减法就是乘除法可以和栈顶的数相结合，而加减法只能把自己压栈
                    //其实也可以全部压栈，最后再计算，但是会很麻烦
                    case '*':
                        pre = stack.poll();
                        stack.push(pre * num);
                        break;
                    case '/':
                        //要求除法仅保留整数部分，直接不做任何处理
                        pre = stack.poll();
                        stack.push(pre / num);
                        break;
                }
                //更新符号为当前符号，数字清零
                sign = c;
                num = 0;
            }
        }
        int res = 0;
        while (!stack.isEmpty()) {
            res += stack.poll();
        }
        return res;
    }
}
