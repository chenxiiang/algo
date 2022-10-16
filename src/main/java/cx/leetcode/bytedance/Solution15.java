package cx.leetcode.bytedance;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Solution15 {
    public List<List<Integer>> threeSum(int[] nums) {
        int n = nums.length;
        List<List<Integer>> res = new ArrayList<>();
        if (n < 3) {
            return res;
        }
        Arrays.sort(nums);
        for (int i = 0; i < n; i++) {
            if (nums[i] > 0) {
                return res;
            }
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }
            int target = -nums[i];
            int left = i + 1, right = n - 1;
            while (left < right) {
                int temp = nums[left] + nums[right];
                if (temp > target) {
                    right--;
                } else if (temp < target) {
                    left++;
                } else {
                    res.add(Arrays.asList(nums[i], nums[left], nums[right]));
                    while (left < right && nums[left + 1] == nums[left]) {
                        left++;
                    }
                    while (left < right && nums[right - 1] == nums[right]) {
                        right--;
                    }
                    left++;
                    right--;
                }
            }
        }
        return res;
    }
}
