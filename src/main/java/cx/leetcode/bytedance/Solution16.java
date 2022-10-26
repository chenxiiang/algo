package cx.leetcode.bytedance;

import java.util.Arrays;

public class Solution16 {
    public int threeSumClosest(int[] nums, int target) {
        if (nums.length < 3) {
            return 0;
        }
        Arrays.sort(nums);
        int ans = nums[0] + nums[1] + nums[2];
//        int start, end = nums.length - 1;   不能在这里写，因为每次i改变后end都要从最后一位开始重新赋值
        for (int i = 0; i < nums.length; i++) {
            //每次固定一个i，然后用双指针从头尾找两个数
            int start = i + 1, end = nums.length - 1;
            while (start < end) {
                int sum = nums[i] + nums[start] + nums[end];
                if (Math.abs(target - sum) < Math.abs(target - ans)) {
                    ans = sum;
                }
                if (sum > target) {
                    end--;
                } else if (sum < target) {
                    start++;
                } else {
                    //sum跟target相等，不会又更接近的数字了，直接返回
                    return ans;
                }
            }
        }
        return ans;
    }
}
