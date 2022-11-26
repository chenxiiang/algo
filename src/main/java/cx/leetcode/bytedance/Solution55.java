package cx.leetcode.bytedance;

public class Solution55 {
    public boolean canJump(int[] nums) {
        int n = nums.length;
        int farthest = 0;  //能到达的最远位置
        //判断能否到达最后一个下标，所以i不用遍历到最后一个下标
        for (int i = 0; i < n - 1; i++) {
            //每一步都按最大的跳，然后更新能到达的最远位置，看是之前跳的远，还是当前这一步跳得远
            farthest = Math.max(farthest, i + nums[i]);
            if (farthest <= i) {
                //能到达的最远位置都比当前位置小，=的情况是最远到当前位置了
                return false;
            }
        }
        return farthest >= n - 1;
    }
}
