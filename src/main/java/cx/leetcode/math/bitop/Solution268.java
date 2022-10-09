package cx.leetcode.math.bitop;

public class Solution268 {
    public int missingNumber(int[] nums) {
        /**
         * 解法一：sum(0,1,..n) - sum(nums)
         */
//        int n = nums.length;
//        long expect = (0 + n) * (n + 1) / 2;
//        long sum = Arrays.stream(nums).sum();
//        return (int) (expect - sum);

        /**
         * 异或，把索引补一位，然后让每个元素和索引做异或，成对的数字都会消失，只有落单的元素会剩下
         */
        int n = nums.length;
        int res = 0;
        res ^= n;
        for (int i = 0; i < n; i++) {
            res = res ^ i ^ nums[i];
        }
        return res;
    }
}
