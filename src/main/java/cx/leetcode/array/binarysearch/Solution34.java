package cx.leetcode.array.binarysearch;

public class Solution34 {
//    public int[] searchRange(int[] nums, int target) {
//        if (nums.length == 0 || target < nums[0] || target > nums[nums.length - 1]) {
//            return new int[]{-1, -1};
//        }
//        return new int[]{findLeft(nums, target), findRight(nums, target)};
//    }
//
//    //左右各一个方法可以求解，但是重复太多，能不能简化？
//    private int findLeft(int[] nums, int target) {
//        int left = 0, right = nums.length - 1;
//        while (left <= right) {
//            int mid = left + (right - left) / 2;
//            if (nums[mid] > target) {
//                right = mid - 1;
//            } else if (nums[mid] < target) {
//                left = mid + 1;
//            } else if (nums[mid] == target) {
//                right = mid - 1;
//            }
//        }
//        if (left >= nums.length || nums[left] != target) {
//            return -1;
//        }
//        return left;
//    }
//
//    private int findRight(int[] nums, int target) {
//        int left = 0, right = nums.length - 1;
//        while (left <= right) {
//            int mid = left + (right - left) / 2;
//            if (nums[mid] > target) {
//                right = mid - 1;
//            } else if (nums[mid] < target) {
//                left = mid + 1;
//            } else if (nums[mid] == target) {
//                left = mid + 1;
//            }
//        }
//        if (right < 0 || nums[right] != target) {
//            return -1;
//        }
//        return right;
//    }

    //简化
//    public int[] searchRange(int[] nums, int target) {
//        int leftIdx = binarySearch(nums, target, true);
//        int rightIdx = binarySearch(nums, target, false) - 1;
//        if (leftIdx <= rightIdx && rightIdx < nums.length && nums[leftIdx] == target && nums[rightIdx] == target) {
//            return new int[]{leftIdx, rightIdx};
//        }
//        return new int[]{-1, -1};
//    }
//
//    //二分查找中，寻找leftIdx即为在数组中寻找第一个大于等于target的下标，寻找rightIdx即为在数组中寻找第一个大于target的下标，然后将下标减一
//
//    private int binarySearch(int[] nums, int target, boolean toLeft) {
//        int left = 0, right = nums.length - 1, ans = nums.length;
//        while (left <= right) {
//            int mid = left + (right - left) / 2;
//            if (nums[mid] > target || (toLeft && nums[mid] >= target)) {
//                // 这就是把上面两个方法中right左移的情况写在一起
//                right = mid - 1;
//                ans = mid;
//            } else {
//                //从上面两个方法可知，其他所有情况都是left右移
//                left = mid + 1;
//            }
//        }
//        return ans;
//    }

    //好像更简单
    //以寻找右边界的方法为例，本来的实现是在得到结果后进行判断，如果不满足则返回-1
    //对其进行修改，首先将参数变为target-1，其次删除末尾的判断的条件，这样就变为寻找target-1的右边界rightBound
    //而无论数组中是否含有target-1，rightBound+1都是target的左边界
    //对于寻找左边界的方法也可以通过改造来直接寻找target的右边界
    public int[] searchRange(int[] nums, int target) {
        int leftIdx = findRight(nums, target - 1) + 1;
        int rightIdx = findRight(nums, target);
        if (leftIdx <= rightIdx && rightIdx < nums.length && nums[leftIdx] == target && nums[rightIdx] == target) {
            return new int[]{leftIdx, rightIdx};
        }
        return new int[]{-1, -1};
    }

    //二分查找中，寻找leftIdx即为在数组中寻找第一个大于等于target的下标，寻找rightIdx即为在数组中寻找第一个大于target的下标，然后将下标减一

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
