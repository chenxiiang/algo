package cx.leetcode.O.kuohao;

public class Solution921 {
    public int minAddToMakeValid(String s) {
        //res 记录插入次数
        int res = 0;
        /**
         * 需要的右括号的个数
         */
        int need = 0;
        char[] chars = s.toCharArray();
        for (int i = 0; i < s.length(); i++) {
            if (chars[i] == '(') {
                //遇到一个左括号就需要一个右括号来对齐
                need++;
            } else {
                //遇到一个右括号，则对右括号的需求-1
                need--;
                if (need == -1) {
                    //这里说明右括号多了，必须通过插入左括号才能解决
                    need = 0;
                    res++;
                }
            }
        }
        //为什么二者相加？res记录左括号的插入次数，need记录右括号的需求
        return res + need;
    }
}
