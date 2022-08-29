package cx.leetcode.array;

public class Solution1109 {
    public int[] corpFlightBookings(int[][] bookings, int n) {
        int[] diff = new int[n];
        for (int[] booking : bookings) {
            int a = booking[0] - 1;
            int b = booking[1];
            diff[a] += booking[2];
            if (b < diff.length) {
                diff[b] -= booking[2];
            }

        }
        for (int i = 1; i < diff.length; i++) {
            diff[i] = diff[i - 1] + diff[i];
        }
        return diff;
    }
}
