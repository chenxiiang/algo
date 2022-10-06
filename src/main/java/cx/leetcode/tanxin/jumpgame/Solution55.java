package cx.leetcode.tanxin.jumpgame;

public class Solution55 {
    public boolean canJump(int[] nums) {
        //每一步都按最大的跳，看能不能到最后一个下标
        int n = nums.length;
        int farthest = 0;
        // i不用到最后一个下标
        for (int i = 0; i < n - 1; i++) {
            farthest = Math.max(farthest, i + nums[i]);
            //遇到0，卡住不动
            if (farthest <= i) {
                return false;
            }
        }
        return farthest >= n - 1;
    }
}
