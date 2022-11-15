package cx.leetcode.bytedance;

public class Solution34 {
    //二分查找中，寻找leftIdx即为在数组中寻找第一个大于等于target的下标
    //寻找rightIdx即为在数组中寻找第一个大于target的下标，然后将下标减一
    public int[] searchRange(int[] nums, int target) {
        //二分查找中，寻找target的左界相当于寻找target-1的右界，然后+1
        int leftIdx = findRight(nums, target - 1) + 1;
        int rightIdx = findRight(nums, target);
        if (leftIdx <= rightIdx && rightIdx < nums.length && nums[leftIdx] == target && nums[rightIdx] == target) {
            //还要判断相等是因为left和right的位置有可能根本不是这两个数
            return new int[]{leftIdx, rightIdx};
        }
        return new int[]{-1, -1};
    }

    //寻找target在nums中的最右的位置
    private int findRight(int[] nums, int target) {
        int left = 0, right = nums.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] > target) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return right;
    }
}
