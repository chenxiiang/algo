package cx.leetcode.array.binarysearch;

import cx.leetcode.array.Solution1094;

public class Solution704 {
    public int search(int[] nums, int target) {
        int left = 0, right = nums.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] > target) {
                right = mid - 1;
            } else if (nums[mid] < target) {
                left = mid + 1;
            } else if (nums[mid] == target) {
                return mid;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        Solution704 solution704 = new Solution704();
        int[] nums = new int[]{0, 1, 3, 5, 5, 7};
        int tar = 5;
        System.out.println(solution704.search(nums, 4));
    }
}
