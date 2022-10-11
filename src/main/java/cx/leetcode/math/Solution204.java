package cx.leetcode.math;

import java.util.Arrays;

/**
 * 给定整数 n ，返回 所有小于非负整数 n 的质数的数量 。
 */
public class Solution204 {
    public int countPrimes(int n) {
        boolean[] isPrime = new boolean[n];
        Arrays.fill(isPrime, true);
        //1不是，2是质数，并且只要遍历到sqrt(n)即可，因为
        // 12 = 2 × 6
        // 12 = 3 × 4
        // 12 = sqrt(12) × sqrt(12)
        // 12 = 4 × 3
        // 12 = 6 × 2
        // sqrt后面的都是重复遍历
        for (int i = 2; i * i < n; i++) {
            //首先从 2 开始，我们知道 2 是一个素数，那么 2 × 2 = 4, 3 × 2 = 6, 4 × 2 = 8… 都不可能是素数了。
            // 然后我们发现 3 也是素数，那么 3 × 2 = 6, 3 × 3 = 9, 3 × 4 = 12… 也都不可能是素数了。
            if (isPrime[i]) {
                // for (int j=2*i;j<n;j+=i)
                //这里不从2倍开始，因为存在冗余计算
                //比如 n = 25，i = 5 时算法会标记 5 × 2 = 10，5 × 3 = 15 等等数字，但是这两个数字已经被 i = 2 和 i = 3 的 2 × 5 和 3 × 5 标记了，直接从本身开始倍数
                for (int j = i * i; j < n; j += i) {
                    isPrime[j] = false;
                }
            }
        }
        int count = 0;
        for (int i = 2; i < n; i++) {
            if (isPrime[i]) {
                count++;
            }
        }
        return count;
    }
}
