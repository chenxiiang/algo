package cx.leetcode.math;

/**
 * 非负整数 K，问你有多少个 n，使得 n! 结果末尾有 K 个 0
 * 搜索有多少个 n 满足 trailingZeroes(n) == K，其实就是在问，满足条件的 n 最小是多少，最大是多少，最大值和最小值一减，就可以算出来有多少个 n 满足条件了
 * 实际上给了限制，K 是在 [0, 10^9] 区间内的整数，也就是说，trailingZeroes(n) 的结果最多可以达到 10^9
 * trailingZeroes(INT_MAX) 的结果，比 10^9 小一些，那再用 LONG_MAX 算一下，远超 10^9 了，所以 LONG_MAX 可以作为搜索的上界
 */
public class Solution793 {
    public int preimageSizeFZF(int k) {
//        return (int) (rightBound(k) - leftBound(k-1) + 1);
//        两种都可以，下面这个可以只写一个方法
        return (int) (rightBound(k) - rightBound(k - 1));
    }

    /* 搜索 trailingZeroes(n) == K 的左侧边界 */
    long leftBound(long target) {
        long low = 0, high = Long.MAX_VALUE;
        while (low <= high) {
            long mid = low + (high - low) / 2;
            if (trailingZeroes(mid) < target) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return low;
    }

    long rightBound(long target) {
        long low = 0, high = Long.MAX_VALUE;
        while (low <= high) {
            long mid = low + (high - low) / 2;
            if (trailingZeroes(mid) > target) {
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        return high;
    }

    // 逻辑不变，数据类型全部改成 long
    long trailingZeroes(long n) {
        long res = 0;
        for (long d = n; d / 5 > 0; d = d / 5) {
            res += d / 5;
        }
        return res;
    }
}
