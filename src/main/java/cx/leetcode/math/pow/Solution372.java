package cx.leetcode.math.pow;

/**
 * 计算 ab 对 1337 取模，a 是一个正整数，b 是一个非常大的正整数且会以数组形式给出
 */
public class Solution372 {
    int base = 1337;

    public int superPow(int a, int[] b) {
        return superP(a, b, b.length);
    }

    private int superP(int a, int[] b, int len) {
        if (len == 0) {
            return 1;
        }
        int last = b[len - 1];
        len--;
        //x^abcd = x^d * (x^abc)^10
        int part1 = myPow(a, last);
        int part2 = myPow(superP(a, b, len), 10);
        return (part2 * part1) % base;
    }

    /**
     * 计算a的k次方，然后对base求模
     * 求模的时候乘法可能会溢出，因此要进行变换
     * (a * b) % k = (a % k)(b % k) % k = [(a % k) % k] * [(b % k) % k] % k
     * 再进行扩展，求一个数的幂就是对这个数连乘
     */
    private int myPow(int a, int k) {
        if (k == 0) {
            return 1;
        }
        //对因子求模
        a %= base;
        int res = 1;
        for (int i = 0; i < k; i++) {
            res *= a;
            res %= base;
        }
        return res;
    }

    //快速求幂
    // int mypow(int a, int k) {
    //     if (k == 0) return 1;
    //     a %= base;
    //
    //     if (k % 2 == 1) {
    //         // k 是奇数
    //         return (a * mypow(a, k - 1)) % base;
    //     } else {
    //         // k 是偶数
    //         int sub = mypow(a, k / 2);
    //         return (sub * sub) % base;
    //     }
    // }
}
