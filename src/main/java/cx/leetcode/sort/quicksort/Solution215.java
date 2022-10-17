package cx.leetcode.sort.quicksort;

import java.util.Random;

public class Solution215 {
    //第k大的就是第n-k小的
    public int findKthLargest(int[] nums, int k) {
        shuffle(nums);
        int low = 0, high = nums.length - 1;
        k = nums.length - k;
        while (low <= high) {
            int p = partition(nums, low, high);
            if (p < k) {
                //要找的元素在右边
                low = p + 1;
            } else if (p > k) {
                //在左边
                high = p - 1;
            } else {
                return nums[p];
            }
        }
        return -1;
    }

    private int partition(int[] nums, int low, int high) {
        int pivot = nums[low];
        // 这里把 i, j 定义为开区间，同时定义：
        // [lo, i) <= pivot；(j, hi] > pivot
        // 之后都要正确维护这个边界区间的定义
        int i = low + 1, j = high;
        // 有break的条件，这里直接true即可
        while (true) {
            while (i < high && nums[i] < pivot) {
                i++;
                // 此 while 结束时恰好 nums[i] >= pivot
            }
            while (j > low && nums[j] > pivot) {
                j--;
                // 此 while 结束时恰好 nums[j] <= pivot
            }

            //需要的是左小右大。但是两个while结束后刚好相反，nums[i]大，nums[j]小
            //但是如果此时已经i>j，那么刚好满足左小右大，否则，就交换i  j的元素
            if (i >= j) {
                break;
            }
            // 此时 [lo, i) < pivot && (j, hi] > pivot
            // 交换 nums[j] 和 nums[i]
            swap(nums, i, j);
            i++;
            j--;
        }
        // 最后将 pivot 放到合适的位置，即 pivot 左边元素较小，右边元素较大
        // 为什么跟j交换？因为break的条件是i>j，并且j处的元素是较小的，所以跟j交换，j就是pivot的最终位置
        swap(nums, low, j);
        return j;
    }

    private void shuffle(int[] nums) {
        Random rand = new Random();
        int n = nums.length;
        for (int i = 0; i < n; i++) {
            int r = rand.nextInt(n - i);
            swap(nums, i, r);
        }
    }

    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}
