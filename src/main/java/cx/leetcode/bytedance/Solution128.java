package cx.leetcode.bytedance;

import java.util.HashSet;
import java.util.Set;

public class Solution128 {
    //这其实可以看做是dp的空间优化版，dp[i]表示的是以下标i为起点的连续序列的的长度
    // dp[0]=1
    // if(dp[i]==dp[i-1]+1);
    // dp[i]=dp[i-1]+1
    public int longestConsecutive(int[] nums) {
        Set<Integer> numSet = new HashSet<>();
        for (int num : nums) {
            numSet.add(num);
        }
        int longest = 0;
        for (int num : numSet) {
            //大部分情况下是不会进入if的，只有当当前元素是连续序列的第一个时才会进入if，然后再计算连续序列的长度，也就是说数组中的每个数只会进入内层循环一次
            if (!numSet.contains(num - 1)) {
                int currNum = num;
                int currLen = 1;
                while (numSet.contains(currNum + 1)) {
                    currNum++;
                    currLen++;
                }
                longest = Math.max(longest, currLen);
            }
        }
        return longest;
    }
}
