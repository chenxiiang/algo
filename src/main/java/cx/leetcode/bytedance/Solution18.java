package cx.leetcode.bytedance;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Solution18 {
    public List<List<Integer>> fourSum(int[] nums, int target) {
        int n = nums.length;
        Arrays.sort(nums);
        if (n < 4) {
            return Collections.emptyList();
        }
        return nSumTarget(nums, 4, 0, target);
    }

    private List<List<Integer>> nSumTarget(int[] nums, int n, int start, long target) {
        int size = nums.length;
        List<List<Integer>> res = new ArrayList<>();
        if (n < 2 || size < n) {
            return res;
        }
        if (n == 2) {
            int low = start, high = size - 1;
            while (low < high) {
                int sum = nums[low] + nums[high];
                int left = nums[low], right = nums[high];
                if (sum < target) {
                    while (low < high && nums[low] == left) {
                        low++;
                    }
                } else if (sum > target) {
                    while (low < high && nums[high] == right) {
                        high--;
                    }
                } else {
                    res.add(new ArrayList<>(Arrays.asList(left, right)));
                    while (low < high && nums[low] == left) {
                        low++;
                    }
                    while (low < high && nums[high] == right) {
                        high--;
                    }
                }
            }
        } else {
            //大于2个数
            for (int i = start; i < size; i++) {
                List<List<Integer>> sub = nSumTarget(nums, n - 1, i + 1, target - nums[i]);
                for (List<Integer> arr : sub) {
                    arr.add(nums[i]);
                    res.add(arr);
                }
                while (i < size - 1 && nums[i] == nums[i + 1]) {
                    i++;
                }
            }
        }
        return res;
    }

//    public static void main(String[] args) {
//        int[] nums = new int[]{1000000000, 1000000000, 1000000000, 1000000000};
//        int target = -294967296;
//        Solution18 solution18 = new Solution18();
//        solution18.fourSum(nums, target);
//    }
}