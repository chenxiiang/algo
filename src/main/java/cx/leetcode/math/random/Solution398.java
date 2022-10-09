package cx.leetcode.math.random;

import java.util.Random;

public class Solution398 {
    int[] nums;
    Random random = new Random();

    public Solution398(int[] nums) {
        this.nums = nums;
    }

    public int pick(int target) {
        int count = 0, res = -1;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != target) {
                continue;
            }
            count++;
            if (0 == random.nextInt(count)) {
                res = i;
            }
        }
        return res;
    }
}
