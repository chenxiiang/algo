package cx.leetcode.sort.mergesort;

public class Solution912 {
    private static int[] temp;

    public int[] sortArray(int[] nums) {
        //提前分配需要的临时空间
        temp = new int[nums.length];
        sort(nums, 0, nums.length - 1);
        return nums;
    }

    private void sort(int[] nums, int low, int high) {
        if (low == high) {
            //单个元素直接返回
            return;
        }
        int mid = low + (high - low) / 2;
        sort(nums, low, mid);
        sort(nums, mid + 1, high);
        merge(nums, low, mid, high);
    }

    private void merge(int[] nums, int low, int mid, int high) {
        //左右两个序列的起始位置
        int i = low, j = mid + 1;
        int t = 0;
        while (i <= mid && j <= high) {
            if (nums[i] <= nums[j]) {
                temp[t++] = nums[i++];
            } else {
                temp[t++] = nums[j++];
            }
        }
        while (i <= mid) {
            temp[t++] = nums[i++];
        }
        while (j <= high) {
            temp[t++] = nums[j++];
        }
        t = 0;
        while (low <= high) {
            nums[low++] = temp[t++];
        }
    }
}
