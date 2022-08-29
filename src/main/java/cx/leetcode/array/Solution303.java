package cx.leetcode.array;

public class Solution303 {
    int[] sumArray;

    public Solution303(int[] nums) {
        sumArray = new int[nums.length + 1];
        for (int i = 1; i < sumArray.length; i++) {
            sumArray[i] = sumArray[i - 1] + nums[i-1];
        }
    }

    public int sumRange(int left, int right) {
        return sumArray[right+1] - sumArray[left];
    }
}
