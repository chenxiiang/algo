package cx.leetcode.bytedance;

public class Solution50 {
    public double myPow(double x, int n) {
        if (n == 0) {
            return 1.0;
        } else if ((n & 1) == 0) {
            return myPow(x * x, n / 2);
        } else {
            return (n > 0 ? x : 1.0 / x) * myPow(x * x, n / 2);
        }
//        if (n == 0) {
//            return 1;
//        }
//        if (n == Integer.MIN_VALUE) {
//            //把指数是最大值的情况单独处理，避免-n整型溢出
//            return myPow(1 / x, -(n + 1)) / x;
//        }
//        if (n < 0) {
//            return myPow(1 / x, -n);
//        }
//        if (n % 2 == 0) {
//            double sub = myPow(x, n / 2);
//            return sub * sub;
//        } else {
//            return x * myPow(x, n - 1);
//        }
    }
}
