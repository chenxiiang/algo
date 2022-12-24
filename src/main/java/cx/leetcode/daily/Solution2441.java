/*
 * Copyright (c) Huawei Technologies Co., Ltd. 2022-2022. All rights reserved.
 */

package cx.leetcode.daily;

import java.util.Arrays;

/**
 * 2441. 与对应负数同时存在的最大正整数
 *
 * @author c00575945
 * @since 2022-12-24
 */
public class Solution2441 {
    public int findMaxK(int[] nums) {
        Arrays.sort(nums);
        int i = 0, j = nums.length - 1;
        while (i < j) {
            int sum = nums[i] + nums[j];
            if (sum == 0) {
                return nums[j];
            } else if (sum > 0) {
                j--;
            } else {
                i++;
            }
        }
        return -1;
    }
}
