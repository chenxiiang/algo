package cx.leetcode.binarytree.mergesort;

import java.util.ArrayList;
import java.util.List;

public class Solution315 {
    public static void main(String[] args) {
        Solution315 solution315 = new Solution315();
        int[] nums = new int[]{1, 3, 5, 2, 4, 6, 7};
        solution315.countSmaller(nums);
    }

    static class Pair {
        int val, idx;

        Pair(int val, int idx) {
            this.val = val;
            this.idx = idx;
        }
    }

    //记录每个元素后面比自己小的元素个数
    private int[] count;

    //归并排序辅助数组
    private Pair[] temp;

    public List<Integer> countSmaller(int[] nums) {
        int n = nums.length;
        count = new int[n];
        temp = new Pair[n];
        Pair[] arr = new Pair[n];
        for (int i = 0; i < n; i++) {
            arr[i] = new Pair(nums[i], i);
        }
        sort(arr, 0, n - 1);
        List<Integer> res = new ArrayList<>();
        for (int c : count) {
            res.add(c);
        }
        return res;
    }

    private void sort(Pair[] arr, int low, int high) {
        if (low == high) {
            return;
        }
        int mid = low + (high - low) / 2;
        sort(arr, low, mid);
        sort(arr, mid + 1, high);
        merge(arr, low, mid, high);
    }

    private void merge(Pair[] arr, int low, int mid, int high) {
        for (int i = low; i <= high; i++) {
            temp[i] = arr[i];
        }
        int i = low, j = mid + 1;
        for (int p = low; p <= high; p++) {
            if (i == mid + 1) {
                //左侧已全部被合并
                arr[p] = temp[j++];
            } else if (j == high + 1) {
                //右侧已全部被合并
                arr[p] = temp[i++];
                //这里可以想下什么情况下j比i先到达末尾，就是i处的元素比j的大，所以也不难想通了
                count[arr[p].idx] += j - mid - 1;
            } else if (temp[i].val > temp[j].val) {
                arr[p] = temp[j++];
            } else {
                //这是由归并排序的性质决定的，当左侧的i已经比右侧的j小的时候，说明[mid+1,j)中的元素都bii处的元素小，这一点要好好理解下
                arr[p] = temp[i++];
                count[arr[p].idx] += j - mid - 1;
            }
        }
    }
}
