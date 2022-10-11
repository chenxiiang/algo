package cx.leetcode.O;

import java.util.LinkedList;
import java.util.List;

public class Solution969 {
    LinkedList<Integer> res = new LinkedList<>();

    public List<Integer> pancakeSort(int[] arr) {
        sort(arr, arr.length);
        return res;
    }

    //这样得到的结果是可以通过的，但是不是最优的
    private void sort(int[] cakes, int n) {
        if (n == 1) {
            return;
        }
        //寻找最大值的索引
        int maxCake = 0;
        int maxCakeIndex = 0;
        for (int i = 0; i < n; i++) {
            if (cakes[i] > maxCake) {
                maxCakeIndex = i;
                maxCake = cakes[i];
            }
        }

        //第一次翻转，把最大值翻到最上面
        reverse(cakes, 0, maxCakeIndex);
        res.add(maxCakeIndex + 1);
        //第二次翻转，将最大值放到最下面
        reverse(cakes, 0, n - 1);
        res.add(n);

        sort(cakes, n - 1);
    }

    //把i到j之间的元素全部翻转
    void reverse(int[] arr, int i, int j) {
        while (i < j) {
            int temp = arr[i];
            arr[i] = arr[j];
            arr[j] = temp;
            i++;
            j--;
        }
    }
}
