package cx.leetcode.dp;

public class Fib {
    public int fib(int n) {
        if (n <= 1) {
            return n;
        }
        int dp1 = 0, dp2 = 1;
        for (int i = 2; i <= n; i++) {
            int sum = dp1 + dp2;
            dp1 = dp2;
            dp2 = sum;
        }
        return dp2;
    }
}
