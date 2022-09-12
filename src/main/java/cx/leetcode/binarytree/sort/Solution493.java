package cx.leetcode.binarytree.sort;

public class Solution493 {
    int count = 0;

    public int reversePairs(int[] nums) {
        sort(nums);
        return count;
    }

    private int[] temp;

    private void sort(int[] nums) {
        temp = new int[nums.length];
        sort(nums, 0, nums.length - 1);
    }

    private void sort(int[] nums, int low, int high) {
        if (low == high) {
            return;
        }
        int mid = low + (high - low) / 2;
        sort(nums, low, mid);
        sort(nums, mid + 1, high);
        merge(nums, low, mid, high);
    }

    private void merge(int[] nums, int low, int mid, int high) {
        for (int i = low; i <= high; i++) {
            temp[i] = nums[i];
        }

        int end = mid + 1;
        //这里最粗暴的就是把左边的跟每个右边的元素*2进行对比，但是会超时
        //注意子数组 nums[lo..mid] 是排好序的，也就是 nums[i] <= nums[i+1]。
        //所以，对于 nums[i], lo <= i <= mid，我们在找到的符合 nums[i] > 2*nums[j] 的 nums[j], mid+1 <= j <= hi，也必然也符合 nums[i+1] > 2*nums[j]。
        //换句话说，我们不用每次都傻乎乎地去遍历整个 nums[mid+1..hi]，只要维护一个开区间边界 end，维护 nums[mid+1..end-1] 是符合条件的元素即可。
        //进行效率优化，维护左闭右开区间 [mid+1, end) 中的元素乘 2 小于 nums[i]
        //为什么 end 是开区间？因为这样的话可以保证初始区间 [mid+1, mid+1) 是一个空区间
        for (int i = low; i <= mid; i++) {
            while (end <= high && (long) nums[i] > (long) nums[end] * 2) {
                end++;
            }
            count += end - (mid + 1);
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
