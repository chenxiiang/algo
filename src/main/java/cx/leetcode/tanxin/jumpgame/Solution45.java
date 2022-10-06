package cx.leetcode.tanxin.jumpgame;

public class Solution45 {
    //假设总能到最后一个，求最少的步数
//    可以用dp来做这道题，但是会超时，因为每一步会递归计算子问题
//    使用贪心算法，只要从第一步选择能到达的坐标中步数最大的，就已经涵盖了dp的子问题
    public int jump(int[] nums) {
        int n = nums.length;
        int end = 0, farthest = 0;
        int jumps = 0;
        for (int i = 0; i < n - 1; i++) {
//            在当前可以到达的坐标中选择最大的作为最远距离
            farthest = Math.max(farthest, i + nums[i]);
//            已经把上一跳所有能到达的位置都遍历完了，end放到可以到达的最远坐标，步数+1
            if (end == i) {
                jumps++;
                end = farthest;
            }
        }
        return jumps;
    }
}
