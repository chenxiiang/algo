package cx.leetcode.math.bitop;

public class Solution231 {
    /**
     * 一个数如果是 2 的指数，那么它的二进制表示一定只含有一个 1
     */
    public boolean isPowerOfTwo(int n) {
        if (n <= 0) {
            return false;
        }
        return (n & (n - 1)) == 0;
    }
}
