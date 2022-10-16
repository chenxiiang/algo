package cx.leetcode.bytedance;

public class Solution7 {
    public int reverse(int x) {
        int res = 0;
        while (x != 0) {
            int temp = x % 10;   //通过取模取最后一位数

            /**
             * 下面两个if分别用来判断最小和最大的边界是否会溢出
             * 因为还在while循环内，所以res至少还会再乘一次10
             * 如果此时已经大于最大值或者小于最小值的1/10，或者虽然等于1/10，但是最后要加的个位数会超出
             * 这就是题目要求的超出32位有符号整数范围后返回0
             */
            //32位整数最大值为2^31-1，也就是2147483647
            //判断是否大于最大整数
            if (res > 214748364 || (res == 214748364 && temp > 7)) {
                return 0;
            }
            //判断是否小于最小32位整数：-2147483648
            if (res < -214748364 || (res == 214748364 && temp < -8)) {
                return 0;
            }
            res = res * 10 + temp;
            x /= 10;
        }
        return res;
    }
}
