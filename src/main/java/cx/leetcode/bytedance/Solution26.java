package cx.leetcode.bytedance;

public class Solution26 {
    public int removeDuplicates(int[] nums) {
        int slow = 0, fast = 0;
        while (fast < nums.length) {
            if (nums[slow] != nums[fast]) {
                slow++;
                nums[slow] = nums[fast];
            }
            fast++;
        }
        //slow是索引，需要的是长度
        return slow + 1;
    }
}
