package cx.leetcode.array;

import java.util.Arrays;
import java.util.PriorityQueue;

public class Solution870 {
    public int[] advantageCount(int[] nums1, int[] nums2) {
        int n = nums1.length;
        PriorityQueue<int[]> maxpq = new PriorityQueue<>((int[] pair1, int[] pair2) -> pair2[1] - pair1[1]);
        // 把nums2的元素以及元素索引按照元素从大到小的顺序放入大顶堆
        for (int i = 0; i < n; i++) {
            maxpq.offer(new int[]{i, nums2[i]});
        }
        //nums1升序排列
        Arrays.sort(nums1);
        //nums[1]是最小值，nums1[right]是最大值
        int left = 0, right = n - 1;
        int[] res = new int[n];
        while (!maxpq.isEmpty()) {
            int[] pair = maxpq.poll();
            //maxval是nums2中的最大值，i是其索引
            int i = pair[0], maxval = pair[1];
            //因为题目中的优势条件只有大于，所以相等的情况就是非优势
            if (maxval < nums1[right]) {
                // 如果nums1中最大值能胜过maxval，就自己上
                res[i] = nums1[right];
                right--;
            } else {
                //否则用最小值送人头
                res[i] = nums1[left];
                left++;
            }
        }
        return res;
    }
}
