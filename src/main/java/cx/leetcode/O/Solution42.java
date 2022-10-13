package cx.leetcode.O;

public class Solution42 {
    public int trap(int[] height) {
        // 首先计算每个位置能存的雨量
        // 位置 i 能达到的水柱高度和其左边的最高柱子、右边的最高柱子有关，我们分别称这两个柱子高度为 l_max 和 r_max；位置 i 最大的水柱高度就是 min(l_max, r_max)
        // 再减去i位置本身的高度即可

        //1、用两个数组来表示i位置左边最高的和右边最高的水柱
        if (height.length == 0) {
            return 0;
        }
        int n = height.length;
        int res = 0;
        int[] lMax = new int[n];
        int[] rMax = new int[n];
        //base case
        lMax[0] = height[0];
        rMax[n - 1] = height[n - 1];
        //从左向右计算lmax
        for (int i = 1; i < n; i++) {
            lMax[i] = Math.max(lMax[i - 1], height[i]);
        }
        //从右向左计算rMax
        for (int i = n - 2; i >= 0; i--) {
            rMax[i] = Math.max(rMax[i + 1], height[i]);
        }
        //求和计算答案
        for (int i = 1; i < n - 1; i++) {
            res = res + Math.min(lMax[i], rMax[i]) - height[i];
        }
        return res;
    }

    //数组中元素每个都只用一次，优化双指针解法
    public int trap1(int[] height) {
        //直接看有点迷惑，先把lmax替换掉
        // int sum = 0;
        // int n = height.length;
        // int maxLeft = 0;
        // int[] rMax = new int[n];
        // for (int i = n - 2; i >= 0; i--) {
        //     rMax[i] = Math.max(rMax[i + 1], height[i + 1]);
        // }
        // for (int i = 1; i < n - 1; i++) {
        //     maxLeft = Math.max(maxLeft, height[i - 1]);
        //     int min = Math.min(maxLeft, rMax[i]);
        //     if (min > height[i]) {
        //         sum += min - height[i];
        //     }
        // }
        // return sum;

        int left = 0, right = height.length - 1;
        int l_max = 0, r_max = 0;

        int res = 0;
        while (left < right) {
            /**
             * 想明白的话这里也不难理解，对照着数组的解法
             */
            l_max = Math.max(l_max, height[left]);
            r_max = Math.max(r_max, height[right]);

            // res += min(l_max, r_max) - height[i]
            //这里每次都只能使用当前拿到的左右最高值中的较小值
            if (l_max < r_max) {
                res += l_max - height[left];
                left++;  //从左到右
            } else {
                res += r_max - height[right];
                right--; //从右到左
            }
        }
        return res;
    }
}
