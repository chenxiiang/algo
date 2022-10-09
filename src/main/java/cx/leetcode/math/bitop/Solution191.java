package cx.leetcode.math.bitop;

public class Solution191 {
    /*
    n & (n-1) 这个操作是算法中常见的，作用是消除数字 n 的二进制表示中的最后一个 1。
     */
    public int hammingWeight(int n) {
        int res = 0;
        while (n != 0) {
            n = n & (n - 1);
            res++;
        }
        return res;
    }
}
