package cx.leetcode.math.random;

import java.util.Arrays;
import java.util.Random;

public class Solution384 {
    private int[] nums;
    private Random random = new Random();

    public Solution384(int[] nums) {
        this.nums = nums;
    }

    public int[] reset() {
        return nums;
    }

    /**
     * 洗牌算法正确性的准则：产生的结果必须有 n! 种可能,因为一个长度为 n 的数组的全排列就有 n! 种，也就是说打乱结果总共有 n! 种。
     * 算法必须能够反映这个事实，才是正确的。
     * 对于 nums[0]，我们把它随机换到了索引 [0, n) 上，共有 n 种可能性；
     * <p>
     * 对于 nums[1]，我们把它随机换到了索引 [1, n) 上，共有 n - 1 种可能性；
     * <p>
     * 对于 nums[2]，我们把它随机换到了索引 [2, n) 上，共有 n - 2 种可能性；
     */
    public int[] shuffle() {
        int n = nums.length;
        int[] copy = Arrays.copyOf(nums, n);
        for (int i = 0; i < n; i++) {
            //r在[i,n)中
            int r = i + random.nextInt(n - i);
            swap(copy, i, r);
        }
        return copy;
    }

    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}
