package cx.leetcode.sort.quicksort;

import java.util.Random;

public class Solution912 {
    public int[] sortArray(int[] nums) {
        //之前为了防止最坏情况出现，都是随机选取元素作为pivot，这里每次开始前对数组进行一次洗牌，然后就选择第一个元素作为pivot
        shuffle(nums);
        quickSort(nums, 0, nums.length - 1);
        return nums;
    }

    private void quickSort(int[] nums, int low, int high) {
        if (low > high) {
            return;
        }
        int p = partition(nums, low, high);
        quickSort(nums, low, p - 1);
        quickSort(nums, p + 1, high);
    }

    //对nums[low..high]进行切分
    private int partition(int[] nums, int low, int high) {
        int pivot = nums[low];
        // 这里把 i, j 定义为开区间，同时定义：
        // [lo, i) <= pivot；(j, hi] > pivot
        // 之后都要正确维护这个边界区间的定义
        int i = low + 1, j = high;
        // 当 i > j 时结束循环，以保证区间 [lo, hi] 都被覆盖
        while (i <= j) {
            while (i < high && nums[i] < pivot) {
                i++;
                // 此 while 结束时恰好 nums[i] > pivot
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
            // 此时 [lo, i) <= pivot && (j, hi] > pivot
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

    //这种方法利用双指针，选最后一个为pivot，j只负责遍历数组，遍历完成后，[low,i-1]都是小的,[i,high-1]都是大等的，最后将pivot放到i上
//    private int partition(int[] nums, int left, int right) {
//        int pivot = nums[right];
//        int i = left, j = left;
//        while (j < right) {
//            if (nums[j] < pivot) {
//                swap(nums, i, j);
//                i++;
//            }
//            j++;
//        }
//        swap(nums, right, i);
//        return i;
//    }

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
