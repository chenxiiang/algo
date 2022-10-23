package cx.leetcode.bytedance;

public class Solution75 {
    /**
     * all in [0, zero] = 0
     * all in (zero, i) = 1
     * all in (two, len - 1] = 2
     */
    public void sortColors(int[] nums) {
        int len = nums.length;
        if (len < 2) {
            return;
        }
        int zeroIdx = -1, twoIdx = len - 1;
        int i = 0;
        //就是一直把0向前挪，把2向后挪，1的位置不动
        while (i <= twoIdx) {
            if (nums[i] == 0) {
                zeroIdx++;
                swap(nums, i, zeroIdx);
                i++;
            } else if (nums[i] == 1) {
                i++;
            } else {
                swap(nums, i, twoIdx);
                twoIdx--;
            }
        }
    }

    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}
