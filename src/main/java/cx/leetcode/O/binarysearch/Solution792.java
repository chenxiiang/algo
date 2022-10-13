package cx.leetcode.O.binarysearch;

import java.util.ArrayList;
import java.util.List;

public class Solution792 {
    //如果要判断多个s，如果还是用双指针复杂度就会上升
    public static int numMatchingSubseq(String s, String[] words) {
        //使用二分法，对t进行预处理，用一个字典index将每个字符出现的索引位置按照顺序进行存储
        List<Integer>[] index = new ArrayList[256];
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (index[c] == null) {
                index[c] = new ArrayList<>();
            }
            index[c].add(i);
        }
        int res = 0;
        for (String word : words) {
            //word上的指针
            int i = 0;
            //s上的指针
            int j = 0;
            //借助index查找word中每个字符的索引
            for (; i < word.length(); i++) {
                char c = word.charAt(i);
                if (index[c] == null) {
                    //s中根本没有c
                    break;
                }
                //在s的c的所有位置上找最左边的j
                //利用二分搜索的性质：当 val 不存在时，得到的索引恰好是比 val 大的最小元素索引
                //当j不是c的位置时，会返回>j的最小元素的位置
                int pos = left_bound(index[c], j);
                if (pos == -1) {
                    //没有找到字符c
                    break;
                }
                //向前移动j,找到了c在正确范围内最靠左的位置，然后以s中c的下一个位置开始找c的后一个元素
                j = index[c].get(pos) + 1;
            }
            if (i == word.length()) {
                res++;
            }
        }
        return res;
    }

    //要找每个元素尽量小的位置，肯定是找左边界，如果元素不在该位置，返回的也是不小于target的左边界
    static int left_bound(List<Integer> arr, int target) {
        int left = 0, right = arr.size() - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (arr.get(mid) < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        if (left == arr.size()) {
            return -1;
        }
        return left;
    }

    public static void main(String[] args) {
//        List<Integer> nums = new ArrayList<>();
//        nums.add(0);
//        nums.add(1);
//        nums.add(3);
//        nums.add(4);
//        System.out.println(left_bound(nums, 6));
        String s = "abcde";
        String[] words = new String[]{"a", "bb", "acd", "ace"};
        System.out.println(numMatchingSubseq(s, words));
    }
}
