package cx.leetcode.binarytree.sort;

public class Solution327 {
    private int lower, upper;

    private int count = 0;

    //为什么对数组排序前后所求的区间数量不会改变呢?
    //寻找满足条件的区间和实际上只需要找到任意两个满足差值为[lower, upper]的前缀和就可以，前缀和的顺序无关紧要

    public int countRangeSum(int[] nums, int lower, int upper) {
        this.lower = lower;
        this.upper = upper;
        long[] preSum = new long[nums.length + 1];
        for (int i = 0; i < nums.length; i++) {
            preSum[i + 1] = preSum[i] + (long) nums[i];
        }
        sort(preSum);
        return count;
    }

    private long[] temp;

    private void sort(long[] nums) {
        temp = new long[nums.length];
        sort(nums, 0, nums.length - 1);
    }

    private void sort(long[] nums, int low, int high) {
        if (low == high) {
            return;
        }
        int mid = low + (high - low) / 2;
        sort(nums, low, mid);
        sort(nums, mid + 1, high);
        merge(nums, low, mid, high);
    }

    private void merge(long[] nums, int low, int mid, int high) {
        if (high - low + 1 >= 0) {
            System.arraycopy(nums, low, temp, low, high - low + 1);
        }

        //维护左闭右开区间 [start, end) 中的元素和 nums[i] 的差在 [lower, upper] 中
        int start = mid + 1, end = mid + 1;
        for (int i = low; i <= mid; i++) {
            while (start <= high && nums[start] - nums[i] < lower) {
                start++;
            }
            while (end <= high && nums[end] - nums[i] <= upper) {
                end++;
            }
            count += end - start;
        }

        int i = low, j = mid + 1;
        for (int p = low; p <= high; p++) {
            if (i == mid + 1) {
                nums[p] = temp[j++];
            } else if (j == high + 1) {
                nums[p] = temp[i++];
            } else if (temp[i] > temp[j]) {
                nums[p] = temp[j++];
            } else {
                nums[p] = temp[i++];
            }
        }
    }
}
