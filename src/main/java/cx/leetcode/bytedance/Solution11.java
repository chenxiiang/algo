package cx.leetcode.bytedance;

public class Solution11 {
    public int maxArea(int[] height) {
        int left = 0, right = height.length - 1;
        int res = 0;
        while (left < right) {
            int currArea = Math.min(height[left], height[right]) * (right - left);
            res = Math.max(res, currArea);
            //这里的指针是大的那个不动，小的向中间移动，因为要求最大值的情况下，而矩形的面积是由较低的两条边决定的，也就是木桶理论，如果不把短的补高，那么高的再高，木桶能装的水也还是那么多
            if (height[left] < height[right]) {
                left++;
            } else {
                right--;
            }
        }
        return res;
    }
}
