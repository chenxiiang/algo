package cx.leetcode.bytedance;

import java.util.PriorityQueue;
import java.util.Random;

/**
 * 找数组中第K大的元素
 * 也就是找第n-k小的元素
 * 要求时间复杂度O(n)
 * 两种方法：
 * 1、优先级队列
 * 2、快排直接寻找第n-k小的，不必完整排序
 */
public class Solution215 {
    //优先级队列
    public int findKthLargest1(int[] nums, int k) {
        //小顶堆
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for (int num : nums) {
            pq.offer(num);
            if (pq.size() > k) {
                pq.poll();
            }
        }
        return pq.peek();
    }

    public int findKthLargest(int[] nums, int k) {
        shuffle(nums);
        int low = 0, high = nums.length - 1;
        //要把第k大的转为第n-k小的,其实把排序过程改一下变为从大到小排序也就不用这样了
        k = nums.length - k;
        while (low <= high) {
            int p = partition(nums, low, high);
            if (p < k) {
                low++;
            } else if (p > k) {
                high--;
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
        int i = low + 1, j = high;
        while (true) {
            while (i < high && nums[i] < pivot) {
                i++;
            }
            while (j > low && nums[j] > pivot) {
                j--;
            }
            if (i >= j) {
                break;
            }
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
        Random random = new Random();
        int n = nums.length;
        for (int i = 0; i < n; i++) {
            //是不是洗牌都无所谓
            int r = random.nextInt(n - i);
            swap(nums, i, r);
        }
    }

    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }


    /**
     * 下面的是实际提交中最快的一个
     */
//    Random random = new Random();
//
//    public int findKthLargest2(int[] nums, int k) {
//        return quickSelect(nums, 0, nums.length - 1, nums.length - k);
//    }
//
//    public int quickSelect(int[] a, int l, int r, int index) {
//        int q = randomPartition(a, l, r);
//        if (q == index) {
//            return a[q];
//        } else {
//            return q < index ? quickSelect(a, q + 1, r, index) : quickSelect(a, l, q - 1, index);
//        }
//    }
//
//    public int randomPartition(int[] a, int l, int r) {
//        int i = random.nextInt(r - l + 1) + l;
//        swap(a, i, r);
//        return partition(a, l, r);
//    }
//
//    public int partition(int[] nums, int low, int high) {
//        int pivot = nums[high];
//        //j只用于遍历数组，遍历完成后，[low,i-1]都是小的，[i,high-1]都是大的，最后将pivot放到i上
//        int i = low, j = low;
//        while (j < high) {
//            //在遍历的过程中如果发现比pivot小的元素，则跟i位置交换
//            if (nums[j] < pivot) {
//                swap(nums, i, j);
//                i++;
//            }
//            //遍历是要一直进行的
//            j++;
//        }
//        //最后把pivot放到i上，i就是分区点
//        swap(nums, high, i);
//        return i;
//    }
//
}


