package cx.leetcode.bytedance;

public class Solution35 {
    //搜索左边界，如果这个数字在数组中存在，返回的是它的第一个索引，如果不存在，则返回的是大于它的最小元素的索引，或者说小于它的元素的个数
    public int searchInsert(int[] nums, int target) {
        int left = 0, right = nums.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return left;
    }
}
