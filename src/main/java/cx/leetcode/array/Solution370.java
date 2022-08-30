package cx.leetcode.array;

public class Solution370 {
    public int[] getModifiedArray(int length, int[][] updates) {
        int[] diff = new int[length];
        for (int[] update : updates) {
            int a = update[0];
            int b = update[1];
            int val = update[2];
            diff[a] += val;
            if (b + 1 < length) {
                diff[b + 1] -= val;
            }
        }
        for (int i = 1; i < length; i++) {
            diff[i] = diff[i - 1] + diff[i];
        }
        return diff;
    }
}
