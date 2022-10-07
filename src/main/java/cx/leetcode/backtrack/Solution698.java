package cx.leetcode.backtrack;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Solution698 {
    public boolean canPartitionKSubsets(int[] nums, int k) {
        if (k > nums.length) {
            return false;
        }
        int sum = Arrays.stream(nums).sum();
        if (sum % k != 0) {
            return false;
        }
        //数据规模 nums.length <= 16，也就是说 used 数组最多也不会超过 16
        //用int类型的used代替used数组
        int used = 0;  //用位图存状态
        int target = sum / k;
        // k 号桶初始什么都没装，从 nums[0] 开始做选择
        return backtrack(k, 0, nums, 0, used, target);
    }

    //存储used数组的状态
    //不用备忘录的话，会存在重复计算的情况，当后面的某个组合不满足，会把前面的都重算，会发生把之前桶1的组合放到桶2中这种情况
    //这样虽然不在同一个桶中，但是实际上是相同的情况
    Map<Integer, Boolean> memo = new HashMap<>();

    //k号桶正在思考是否应该把 nums[start] 这个元素装进来
    //目前 k 号桶里面已经装的数字之和为 bucket
    //used 标志某一个元素是否已经被装到桶中
    //target 是每个桶需要达成的目标和
    public boolean backtrack(int k, int bucket, int[] nums, int start, int used, int target) {
        if (k == 0) {
            return true;
        }

        if (bucket == target) {
            //当前桶装满，开始下一个桶，从nums[0]开始选数字
            boolean res = backtrack(k - 1, 0, nums, 0, used, target);
            memo.put(used, res);
            return res;
        }

        if (memo.containsKey(used)) {
            return memo.get(used);
        }

        for (int i = start; i < nums.length; i++) {
            if (((used >> i) & 1) == 1) {
                //nums[i]已经装入，跳过
                continue;
            }
            if (nums[i] + bucket > target) {
                //超出了所需的和
                continue;
            }
            used |= 1 << i;  //将第i为置为1
            bucket += nums[i];
            if (backtrack(k, bucket, nums, i + 1, used, target)) {
                return true;
            }
            used ^= 1 << i;   //通过异或将第i为恢复为0
            bucket -= nums[i];
        }
        return false;
    }
}
