package cx.leetcode.dp;

import java.util.Arrays;

public class Solution416 {
    // 先对集合求和，得出 sum，把问题转化为背包问题：
    // 给一个可装载重量为 sum / 2 的背包和 N 个物品，每个物品的重量为 nums[i]。现在让你装物品，是否存在一种装法，能够恰好将背包装满？
    public boolean canPartition(int[] nums) {
//        优化版
        int n = nums.length;
        int sum = Arrays.stream(nums).sum();
        if (sum % 2 != 0 || n < 2) {
            return false;
        }
        sum = sum / 2;
        boolean[] dp = new boolean[sum + 1];
        dp[0] = true;
        for (int i = 0; i < n; i++) {
            for (int j = sum; j >= 0; j--) {
//                关于这里的逆向遍历
//                1.先看原来2D情况，dp[i][j]的值，都是仅依靠上一行dp[i-1][...]得出的。意思是我们要算当前dp[i]行的值，仅需要上一行dp[i-1]就好。所以可以将其转化为：原地更新1D数组问题；
//
//                2.现在考虑降为1D，定义该1D数组为int[] dp。回忆原来2D情况，dp[i][j]的值都是依靠“其正上方的值dp[i-1][j]+左上方的值dp[i-1][j-nums[i]]”来更新。那么如果对1D进行正向遍历即从dp[0]->dp[n-1]填充，对于某一位例如dp[cur]的更新，势必会用到dp[pre]（pre<cur），因为是正向遍历，那么dp[pre]在当前轮次已经被更新过了，当在这种情况下计算的dp[cur]肯定不正确（其实说白了，就相当于2D情况下，使用了同一行的值。例如使用dp[i][j-nums[i]]来更新dp[i][j]）；
//
//                3.现在解释对1D数组进行反向遍历即从dp[n-1]->dp[0]填充。同样，对于某一位例如dp[cur]的更新，势必会用到dp[pre]（pre<cur）。但注意，因为是从后往前进行遍历的，此时dp[pre]在当前轮次未被更新，所以就相当于2D情况下使用的上一行的值，这样计算就是正确的了。
                
                    // 倒序遍历是为了保证物品i只被放入一次！。但如果一旦正序遍历了，那么物品0就会被重复加入多次！
                    //
                    // 举一个例子：物品0的重量weight[0] = 1，价值value[0] = 15
                    //
                    // 如果正序遍历
                    //
                    // dp[1] = dp[1 - weight[0]] + value[0] = 15
                    //
                    // dp[2] = dp[2 - weight[0]] + value[0] = 30
                    //
                    // 此时dp[2]就已经是30了，意味着物品0，被放入了两次，所以不能正序遍历。
                    //
                    // 为什么倒序遍历，就可以保证物品只放入一次呢？
                    //
                    // 倒序就是先算dp[2]
                    //
                    // dp[2] = dp[2 - weight[0]] + value[0] = 15 （dp数组已经都初始化为0）
                    //
                    // dp[1] = dp[1 - weight[0]] + value[0] = 15
                    //
                    // 所以从后往前循环，每次取得状态不会和之前取得状态重合，这样每种物品就只取一次了。
                    //
                    // 那么问题又来了，为什么二维dp数组历的时候不用倒序呢？
                    //
                    // 因为对于二维dp，dp[i][j]都是通过上一层即dp[i - 1][j]计算而来，本层的dp[i][j]并不会被覆盖！
                if (j >= nums[i]) {
//                    装上i||不装i
                    dp[j] = dp[j] || dp[j - nums[i]];
                }
            }
        }
        return dp[sum];
    }

    public boolean canPartition1(int[] nums) {
        int n = nums.length;
        int sum = Arrays.stream(nums).sum();
        if (sum % 2 != 0 || n < 2) {
            return false;
        }
        sum = sum / 2;
        boolean[][] dp = new boolean[n + 1][sum + 1];
        for (int i = 0; i <= n; i++) {
            dp[i][0] = true;
        }
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= sum; j++) {
                if (j < nums[i - 1]) {
                    dp[i][j] = dp[i - 1][j];
                } else {
                    dp[i][j] = dp[i - 1][j] || dp[i - 1][j - nums[i - 1]];
                }
            }
        }
        return dp[n][sum];
    }
}
