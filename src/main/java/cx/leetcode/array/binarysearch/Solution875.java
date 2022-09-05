package cx.leetcode.array.binarysearch;

public class Solution875 {
    public int minEatingSpeed(int[] piles, int h) {
        int left = 1, right = 1000000001;
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (f(piles, mid) > h) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        return left;
    }

    private int f(int[] piles, int k) {
        int hours = 0;
        for (int pile : piles) {
            hours += pile / k;
            if (pile % k != 0) {
                hours++;
            }
        }
        return hours;
    }

    public static void main(String[] args) {
        Solution875 solution875 = new Solution875();
        int[] piles = new int[]{805306368, 805306368, 805306368};
        int h = 1000000000;
        System.out.println(solution875.minEatingSpeed(piles, h));
    }
}
