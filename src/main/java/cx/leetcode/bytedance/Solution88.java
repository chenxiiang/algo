package cx.leetcode.bytedance;

public class Solution88 {
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        int i = m - 1, j = n - 1;
        int p = nums1.length - 1;
        while (i >= 0 && j >= 0) {
            if (nums1[i] > nums2[j]) {
                nums1[p] = nums1[i];
                i--;
            } else {
                nums1[p] = nums2[j];
                j--;
            }
            p--;
        }
        //本来就是往nums1中放元素，只要考虑nums2是否遍历完
        while (j >= 0) {
            nums1[p]=nums2[j];
            p--;
            j--;
        }
    }
}
