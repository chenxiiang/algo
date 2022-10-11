package cx.leetcode.O.calculator;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * 字符串中会有空格
 * 因为括号具有递归的性质，所以
 * calculate(3 * (4 - 5/2) - 6)
 * = 3 * calculate(4 - 5/2) - 6
 * = 3 * 2 - 6
 * = 0
 * 遇到左括号开始递归，右括号结束递归
 */
public class Solution224 {
    int i = 0;


    public int calculate(String s) {
        return helper(s);
    }

    public int helper(String s) {
        Deque<Integer> stack = new ArrayDeque<>();
        //记录算式中的数字
        int num = 0;
        //记录num前的符号，初始化为+
        char sign = '+';
        for (; i < s.length(); i++) {
            char c = s.charAt(i);
            if (Character.isDigit(c)) {
                num = 10 * num + (c - '0');
            }
            if (c == '(') {
                i++;
                num = helper(s);
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
                    case '*':
                        pre = stack.poll();
                        stack.push(pre * num);
                        break;
                    case '/':
                        pre = stack.poll();
                        stack.push(pre / num);
                        break;
                }
                //更新符号为当前符号，数字清零
                sign = c;
                num = 0;
            }
            if (c == ')') {
                break;
            }
        }
        int res = 0;
        while (!stack.isEmpty()) {
            res += stack.poll();
        }
        return res;
    }


/**
 * 直接用这个方法会有用例失败，比如 "(1+(4+5+2)-3)+(6+8)"，应该是23，但是结果是37
 * 当递归计算4+5+2后退回上一层，本来应该开始-3，但是因为之前的索引没变，又开始从4开始计算，所以有重复计算
 * 修改的方法就是把当前遍历的索引提取为全局变量
 */
//    public int calculate(String s) {
//        Deque<Integer> stack = new ArrayDeque<>();
//        //记录算式中的数字
//        int num = 0;
//        //记录num前的符号，初始化为+
//        char sign = '+';
//        for (int i = 0; i < s.length(); i++) {
//            char c = s.charAt(i);
//            if (Character.isDigit(c)) {
//                num = 10 * num + (c - '0');
//            }
//            if (c == '(') {
//                num = calculate(s.substring(i + 1));
//            }
//            //如果不是数字，那就是遇到了下一个符号，就把前面的数字和符号都压栈，最后一个数字也压栈
//            //因为存在空格，所以加个判断
//            if ((!Character.isDigit(c) && c != ' ') || i == s.length() - 1) {
//                int pre;
//                switch (sign) {
//                    case '+':
//                        stack.push(num);
//                        break;
//                    case '-':
//                        stack.push(-num);
//                        break;
//                    //乘除法优先于加减法就是乘除法可以和栈顶的数相结合，而加减法只能把自己压栈
//                    //其实也可以全部压栈，最后再计算，但是会很麻烦
//                    case '*':
//                        pre = stack.poll();
//                        stack.push(pre * num);
//                        break;
//                    case '/':
//                        pre = stack.poll();
//                        stack.push(pre / num);
//                        break;
//                }
//                //更新符号为当前符号，数字清零
//                sign = c;
//                num = 0;
//            }
//            if (c == ')') {
//                break;
//            }
//        }
//        int res = 0;
//        while (!stack.isEmpty()) {
//            res += stack.poll();
//        }
//        return res;
//    }
}
