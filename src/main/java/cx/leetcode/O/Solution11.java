package cx.leetcode.O;

public class Solution11 {
    public int maxArea(int[] height) {
        int left = 0, right = height.length - 1;
        int res = 0;
        while (left < right) {
            int curArea = Math.min(height[left], height[right]) * (right - left);
            res = Math.max(res, curArea);
            //移动较低的一边，因为要求的是最大值，移动较低的一边可能会使面积变大，而移动较高的边，最好的情况是矩形面积不变，因为木桶效应，因为求面积时有个Math.min
            if (height[left] < height[right]) {
                left++;
            } else {
                right--;
            }
        }
        return res;
    }
}
