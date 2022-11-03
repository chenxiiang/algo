package cx.leetcode.bytedance;

public class Solution9 {
    //判断数字是否是回文数
    public boolean isPalindrome(int x) {
        //把数字转为字符串的方法不用说，这里要看不转字符串的方法
        if (x < 0) {
            //前面有个负号，显然不是回文
            return false;
        }
        //原数字从前往后除，然后加个变量把得到的地位放到高位再造一个数字，最后看二者是否相等
        int rem = 0, y = 0;
        int quo = x;
        while (quo != 0) {
            rem = quo % 10;
            y = y * 10 + rem;
            quo = quo / 10;
        }
        return x == y;
    }
}
