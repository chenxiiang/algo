package cx.leetcode.bytedance;

public class Solution4 {
    //每次从两个数组中往后找出k/2个数，因为都是排好序的，比较各自的第k/2个数，小的那个数组前面的数组都删了，然后k-k/2继续找，直到k为1
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int m = nums1.length, n = nums2.length;
        //如果总长度是偶数个，中位数有两个数
        //将k是奇数偶数的情况合并，如果是奇数，会求两次同样的k
        //比如总长度为7，中位数就是第4个，这样left和right都是4
        int left = (m + n + 1) / 2;
        int right = (m + n + 2) / 2;
        return (getKth(nums1, 0, m - 1, nums2, 0, n - 1, left) + getKth(nums1, 0, m - 1, nums2, 0, n - 1, right)) * 0.5;
    }

    /**
     * 这个方法的目的是获取第k小的值，有序数组中也就是第k个值
     */
    private int getKth(int[] nums1, int start1, int end1, int[] nums2, int start2, int end2, int k) {
        int len1 = end1 - start1 + 1;
        int len2 = end2 - start2 + 1;
        if (len1 > len2) {
            //用nums1来表示较短的数组，这样保证如果有数组空了，一定是nums1
            return getKth(nums2, start2, end2, nums1, start1, end1, k);
        }
        if (len1 == 0) {
            //直接返回第k个值
            return nums2[start2 + k - 1];
        }
        if (k == 1) {
            return Math.min(nums1[start1], nums2[start2]);
        }
        //因为k有可能比数组长度还大
        int i = start1 + Math.min(len1, k / 2) - 1;
        int j = start2 + Math.min(len2, k / 2) - 1;
        //当前元素较小的数组，前面的元素都不要了
        if (nums1[i] > nums2[j]) {
            return getKth(nums1, start1, end1, nums2, j + 1, end2, k - (j - start2 + 1));
        } else {
            return getKth(nums1, i + 1, end1, nums2, start2, end2, k - (i - start1 + 1));
        }
    }
}
