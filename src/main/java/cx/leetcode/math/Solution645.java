package cx.leetcode.math;

public class Solution645 {
    /**
     * 关键是元素和索引是成对儿出现的
     * 通过将每个索引对应的元素变成负数，以表示这个索引被对应过一次了
     * 这是映射的方法，还可以排序，排序后元素和索引不对应的就能拿到确实和重复的元素
     */
    public int[] findErrorNums(int[] nums) {
        int n = nums.length;
        int dup = -1;
        int missing = -1;
        for (int i = 0; i < n; i++) {
            //因为元素是从1到n，要对应到索引就要减1
            int index = Math.abs(nums[i]) - 1;
            if (nums[index] < 0) {
                //说明这里的元素已经被对应过一次了
                dup = Math.abs(nums[i]);
            } else {
                nums[index] *= -1;
            }
        }

        for (int i = 0; i < n; i++) {
            if (nums[i] > 0) {
                //把索引转换成元素
                missing = i + 1;
            }
        }
        return new int[]{dup, missing};
    }
}
