package cx.leetcode.math;

/**
 * 两个数相乘结果末尾有 0，一定是因为两个数中有因子 2 和 5，因为 10 = 2 x 5。
 * 问题转化为：n! 最多可以分解出多少个因子 2 和 5？
 * 每个偶数都能分解出因子 2，因子 2 肯定比因子 5 多得多。
 * 问题转化为：n! 最多可以分解出多少个因子 5？
 */
public class Solution172 {
    public int trailingZeroes(int n) {
        int res = 0;
        long divisor = 5;
        while (divisor <= n) {
            res += n / divisor;
            divisor *= 5;
        }
        return res;
    }
}
