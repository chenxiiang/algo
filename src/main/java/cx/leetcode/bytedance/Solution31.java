package cx.leetcode.bytedance;

public class Solution31 {
    //先从后往前找到非递增的第一个数，然后与从后往前比它大的第一个数（也就是大于它的数中最小的那个）交换，最后将交换位置后面的数从小到大排序
    public void nextPermutation(int[] nums) {
        int n = nums.length;
        if (n <= 1) {
            return;
        }
        int i = n - 2, j = n - 1, k = n - 1;
        //找到第一个非递增的数nums[i]
        while (i >= 0 && nums[i] >= nums[j]) {
            i--;
            j--;
        }
        if (i >= 0) {//不是最后一个排列
            //再找后面比nums[i]大的数中最小的那个，相等也包含
            while (nums[i] >= nums[k]) {
                k--;
            }
            //swap nums[i] nums[k]
            int temp = nums[i];
            nums[i] = nums[k];
            nums[k] = temp;
        }

        //reverse nums[j..end]
        int left = j, right = n - 1;
        while (left < right) {
            int temp = nums[left];
            nums[left] = nums[right];
            nums[right] = temp;
            left++;
            right--;
        }
    }
}
