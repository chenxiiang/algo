package cx.leetcode.bytedance;

import java.util.Arrays;

public class Solution135 {
    //1、双向遍历
    public int candy1(int[] ratings) {
        int n = ratings.length;
        int[] left = new int[n];
        Arrays.fill(left, 1);
        for (int i = 1; i < n; i++) {
            if (ratings[i] > ratings[i - 1]) {
                left[i] = left[i - 1] + 1;
            }
        }
        int res = left[n - 1];
        int[] right = new int[n];
        Arrays.fill(right, 1);
        for (int i = n - 2; i >= 0; i--) {
            if (ratings[i] > ratings[i + 1]) {
                right[i] = right[i + 1] + 1;
            }
            res += Math.max(left[i], right[i]);
        }
        return res;
    }

    //一次遍历
    //其实如果一直是递增的情况就比较好考虑，问题是如果后面出现了递减的不光要给自己+1，还要给前面比自己大的元素都+1
    //所以用一个变量来记录当前递减的元素的数量，当后面出现了比自己小的元素，则要给这个递减序列中所有的都加1
    public int candy(int[] ratings) {
        int n = ratings.length;
        //inc 最近的递增长度  desc 当前递减序列的长度  pre 前一个人分到的数量
        int res = 1, inc = 1, desc = 0, pre = 1;
        for (int i = 1; i < n; i++) {
            if (ratings[i] >= ratings[i - 1]) {
                desc = 0;   //开始非递减了，则递减序列长度改为0
                pre = ratings[i] == ratings[i - 1] ? 1 : pre + 1;   //看到底是相等还是大于
                res += pre;
                inc = pre;  //递增序列长度在递减的时候也要用，所以用个单独的变量inc存储
            } else {
                desc++;
                if (desc == inc) {
                    //如果当前递减的序列长度和之前递增的序列长度一样了，则必须给开始递减的元素的前一个也+1，否则不满足分配条件了
                    desc++;
                }
                res += desc;
                pre = 1;
            }
        }
        return res;
    }
}
