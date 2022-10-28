package cx.leetcode.bytedance;

public class Solution154 {
    public int findMin(int[] nums) {
        int left = 0, right = nums.length - 1;
        while (left < right) {
            int mid = (left + right) / 2;
            if (nums[mid] > nums[right]) {
                left = mid + 1;
            } else if (nums[mid] < nums[right]) {
                right = mid;
            } else {
                //如果相等，因为元素可重复，不知道左边是什么情况，只能收缩右边界，因为一定有一个它的替代品就是nums[mid]
                right--;
            }
        }
        return nums[left];
    }
}
