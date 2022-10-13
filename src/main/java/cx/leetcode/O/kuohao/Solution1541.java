package cx.leetcode.O.kuohao;

public class Solution1541 {
    public int minInsertions(String s) {
        //res是插入次数，左右的插入都能算，need是需要的右括号
        //可以理解为res是遍历过程汇总插入的次数，而need是当前遍历完成后还需要的右括号的个数
        int res = 0, need = 0;
        char[] chars = s.toCharArray();
        for (int i = 0; i < s.length(); i++) {
            if (chars[i] == '(') {
                need += 2;
                if (need % 2 == 1) {
                    //这种情况发生在右括号只有1个的时候又出现了一个左括号，这是必须先把上一个左括号不足的右括号补齐
                    //也就是这种情况： ()(
                    //这种情况可能是字符串本身就这样，也可能是else的情况中，出现了多于的右括号，所以先插入一个左括号造成的
                    res++;   //表示插入一个右括号
                    need--;  //右括号需求-1
                }
            } else {
                need--;
                if (need == -1) {
                    //多了一个右括号，必须插入一个左括号，但是右括号又再需要一个
                    res++;
                    need = 1;
                }
            }
        }
        return res + need;
    }
}
