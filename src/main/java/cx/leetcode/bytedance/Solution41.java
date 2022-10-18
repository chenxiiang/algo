package cx.leetcode.bytedance;

public class Solution41 {
    //要求O(n)时间 O(1)空间
    //hashset空间不满足 O(n)，二分查找要先排序，排序时间+查找每个元素的时间不满足O(NlogN)

    //将数组视为哈希表
    public int firstMissingPositive(int[] nums) {
        int len = nums.length;
        for (int i = 0; i < len; i++) {
            while (nums[i] > 0 && nums[i] < len && nums[nums[i] - 1] != nums[i]) {
                // 要找的是正整数，<=0的都不考虑(如果按照索引和元素对应的话，就把0位置浪费了)，元素值超出数组长度的也不考虑，已经在指定位置上的也不用交换
                // 也就是满足在指定范围内、并且没有放在正确的位置上，才交换
                // 例如：数值 3 应该放在索引 2 的位置上
                swap(nums, nums[i] - 1, i);
            }
        }

        for (int i = 0; i < len; i++) {
            if (nums[i] != i + 1) {
                return i + 1;
            }
        }
        //否则返回数组长度+1
        return len + 1;
    }

    private void swap(int[] nums, int index1, int index2) {
        int temp = nums[index1];
        nums[index1] = nums[index2];
        nums[index2] = temp;
    }
}
