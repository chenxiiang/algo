package cx.leetcode.daily;

public class Solution1806 {
    public int reinitializePermutation(int n) {
        int i = 1, step = 1;
        while (true) {
            i = i % 2 == 0 ? i / 2 : (n - 1 + i) / 2;
            if (i == 1) return step;
            step++;
        }
    }
}
