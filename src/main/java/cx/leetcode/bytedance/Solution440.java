package cx.leetcode.bytedance;

/**
 * 就是一棵十叉树
 * 重点！！！
 */
public class Solution440 {
    public int findKthNumber(int n, int k) {
        int cnt = 0;    //表示经过的元素的个数，即当经过元素cnt==k-1的时候，下一个就是答案
        int num = 1;      //  经过cnt个元素后的第一个元素
        //让cnt经过k-1个元素，然后返回此时的num
        while (true) {
            if (cnt == k - 1) {
                break;
            }
            int temp = count((long) num, n);    //找到以当前num为根节点的子树的节点的个数总和
            if (cnt + temp < k) {
                //第k个数不在当前前缀下
                num++;
                cnt += temp;//将当前节点的全部子节点设置为已访问过
            } else {
                // cnt + temp >= k，说明，全部设置为经过就太多了，也就是说第k个数在当前前缀下，所以应该进去看看
                num *= 10;
                cnt++;  //只把当前的根节点设为跳过
            }
        }
        return num;
    }

    /**
     * 以当前数字为前缀的十叉树的元素的总个数，包括当前数字
     * nums*10可能会溢出，所以用long
     * num:当前数字
     * n:数字的最大值
     * 注意 纯数字的字典序为 十叉树
     * 1
     * 10 11 12 13 14 15 16 17 18 19  // 10 个
     * 100 .......................199 // 100个
     */
    private int count(long num, int n) {
        int cnt = 0;    //元素总个数
        int width = 1;     //当前层数的宽度，第一层只有num一个元素，所以第一层宽度为1
        while (true) {
            if (num + width - 1 <= n) {     //n 的值大于当前层的最大值，所以当前层的元素可以完全被统计到 cnt 当中
                cnt += width;
                num *= 10;
                width *= 10;        // 更新到更深一层的头部 1 -> 10 -> 100
            } else {
                if (n - num >= 0) {
                    cnt += n - num + 1;
                }
                //满足if说明还在当前的子树上，否则不在当前的树上，比如num=1,n=2的情况，现在num已经是10，但是最大值n其实是2
                // 从调用的地方可以看出，因为下一步会给num++,也就是说开始计算以2为根节点的树上的元素数量
                break;
            }
        }
        return cnt;
    }
}
