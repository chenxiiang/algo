/*
 * Copyright (c) Huawei Technologies Co., Ltd. 2023-2023. All rights reserved.
 */

package cx.leetcode.daily;

/**
 * 2241. 设计一个 ATM 机器
 *
 * @author c00575945
 * @since 2023-01-10
 */
public class ATM {
    int[] cash = new int[] {20, 50, 100, 200, 500};

    long[] count;

    public ATM() {
        count = new long[5];
    }

    public void deposit(int[] banknotesCount) {
        for (int i = 0; i < 5; i++) {
            count[i] += banknotesCount[i];
        }
    }

    public int[] withdraw(int amount) {
        int[] ans = new int[5];
        for (int i = 4; i >= 0; i--) {
            int k = amount / cash[i];
            if (k <= count[i]) {
                ans[i] = k;
                amount -= k * cash[i];
            } else {
                ans[i] = (int) count[i];
                amount -= ans[i] * cash[i];
            }
        }
        if (amount > 0) {
            return new int[] {-1};
        }
        for (int i = 0; i < 5; i++) {
            count[i] -= ans[i];
        }
        return ans;
    }
}
