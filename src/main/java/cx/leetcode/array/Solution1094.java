package cx.leetcode.array;

public class Solution1094 {
    public boolean carPooling(int[][] trips, int capacity) {
        int[] diff = new int[1001];
        for (int[] trip : trips) {
            int a = trip[1];
            int b = trip[2];
            diff[a] += trip[0];
            diff[b] -= trip[0];
        }
        int total = 0;
        for (int i : diff) {
            total += i;
            if (total > capacity) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        Solution1094 solution1094 = new Solution1094();
        int[][] trips = new int[][]{{9, 0, 1}, {3, 3, 7}};
        System.out.println(solution1094.carPooling(trips, 4));
    }
}
