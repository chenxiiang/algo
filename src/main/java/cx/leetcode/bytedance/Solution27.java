package cx.leetcode.bytedance;

public class Solution27 {
    public int removeElement(int[] nums, int val) {
        //这样的算法在最坏情况下（输入数组中没有元素等于val），左右指针各遍历了数组一次。
        int slow = 0, fast = 0;
        while (fast < nums.length) {
            if (nums[fast] != val) {
                nums[slow] = nums[fast];
                slow++;
            }
            fast++;
        }
        return slow;
    }

    //优化
    public int removeElement2(int[] nums, int val) {
        int left = 0, right = nums.length - 1;
        while (left <= right) {
            if (nums[left] == val) {
                nums[left] = nums[right];
                right--;
            } else {
                left++;
            }
        }
        return left;
    }
}
