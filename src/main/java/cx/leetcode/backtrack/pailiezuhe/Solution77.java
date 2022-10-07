package cx.leetcode.backtrack.pailiezuhe;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Solution77 {
    /**
     * 组合
     * 元素无重复不可复选
     */
    List<List<Integer>> res = new ArrayList<>();
    //记录回溯的递归路径
    LinkedList<Integer> track = new LinkedList<>();
    public List<List<Integer>> combine(int n, int k) {
        backtrack(1, n, k);
        return res;
    }

    public void backtrack(int start, int n, int k) {
        if (k == track.size()) {
            res.add(new LinkedList<>(track));
        }
        for (int i = start; i <= n; i++) {
            track.addLast(i);
            backtrack(i + 1, n, k);
            track.removeLast();
        }
    }
}
