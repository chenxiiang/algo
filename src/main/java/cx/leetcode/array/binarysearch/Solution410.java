package cx.leetcode.array.binarysearch;

public class Solution410 {
    public int splitArray(int[] nums, int m) {
        int left = 0, right = 0;
        for (int num : nums) {
            // 确定范围，题目中说每个子数组不为空，所以子数组各自和的最大值有两种情况：
            // 一个元素为一个数组，此时子数组最大和就是nums中最大的元素
            // 只有一个子数组，也就是nums本身，子数组和就是nums所有元素和
            left = Math.max(left, num);
            right += num;
        }
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (f(nums, mid) > m) {
                // 如果子数组数量太多，说明各自和的最大值太小，需要将各自和的最大值调大
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return left;
    }

    //x是子数组最大和，返回值为子数组数目，也就相当于1011题的天数，返回值是分割的子数组个数
    private int f(int[] nums, int x) {
        int num = 0;
        for (int i = 0; i < nums.length; ) {
            int maxSum = x;
            while (i < nums.length) {
                if (maxSum < nums[i]) {
                    break;
                } else {
                    maxSum -= nums[i];
                }
                i++;
            }
            num++;
        }
        return num;
    }
}
