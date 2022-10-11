package cx.leetcode.O;

import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class Solution659 {
    public boolean isPossible(int[] nums) {
        Map<Integer, Integer> freq = new HashMap<>();
        Map<Integer, Integer> need = new HashMap<>();
        for (int num : nums) {
            freq.put(num, freq.getOrDefault(num, 0) + 1);
        }
        for (int n : nums) {
            if (freq.get(n) == 0) {
                //已经全部被用于其他子序列
                continue;
            }
            //判断n能够接到其他子序列后面
            if (need.containsKey(n) && need.get(n) > 0) {
                //n可以接到其他子序列后面
                //对n的需求-1
                need.put(n, need.get(n) - 1);
                freq.put(n, freq.get(n) - 1);
                need.put(n + 1, need.getOrDefault(n + 1, 0) + 1);
            } else if (freq.containsKey(n) && freq.containsKey(n + 1) && freq.containsKey(n + 2) && freq.get(n) > 0
                    && freq.get(n + 1) > 0 && freq.get(n + 2) > 0) {//如果需要至少k个连续元素的数组，这里的条件就判断k个
                //不能接到其他子序列后面，将n作为开头，重新开一个长度为3的子序列[n,n+1,n+2]
                freq.put(n, freq.get(n) - 1);
                freq.put(n + 1, freq.get(n + 1) - 1);
                freq.put(n + 2, freq.get(n + 2) - 1);
                //对n+3的需求+1
                need.put(n + 3, need.getOrDefault(n + 3, 0) + 1);
            } else {
                return false;
            }
        }
        return true;
    }

    //如果需要的不是布尔值，而是要把所有的子序列都打印出来，可以把need改为Map<Integer, List<List<Integer>>>，表示可以以integer结尾的所有的子序列，每个子序列是是一个List<Integer>
    boolean isPossible1(int[] nums) {
        Map<Integer, Integer> freq = new HashMap<>();
        Map<Integer, LinkedList<LinkedList<Integer>>> need = new HashMap<>();
        Arrays.stream(nums).forEach(num -> freq.put(num, freq.getOrDefault(num, 0) + 1));
        for (int n : nums) {
            if (freq.get(n) == 0) {
                continue;
            }
            if (need.containsKey(n) && need.get(n).size() > 0) {
                //可以接到已有序列后面
                freq.put(n, freq.get(n) - 1);
                //随便取一个能以n结尾的序列
                LinkedList<Integer> seq = need.get(n).pollLast();
                seq.addLast(n);
                need.putIfAbsent(n + 1, new LinkedList<>());
                need.get(n + 1).offerLast(seq);
            } else if (freq.containsKey(n) && freq.containsKey(n + 1) && freq.containsKey(n + 2) && freq.get(n) > 0
                    && freq.get(n + 1) > 0 && freq.get(n + 2) > 0) {
                //将n作为开头
                freq.put(n, freq.get(n) - 1);
                freq.put(n + 1, freq.get(n + 1) - 1);
                freq.put(n + 2, freq.get(n + 2) - 1);
                //新建一个长度为3的子序列
                LinkedList<Integer> seq = new LinkedList<>();
                seq.addLast(n);
                seq.addLast(n + 1);
                seq.addLast(n + 2);
                need.putIfAbsent(n + 3, new LinkedList<>());
                need.get(n + 3).offerLast(seq);
            } else {
                return false;
            }
        }

        //打印切分出的所有子序列
        for (LinkedList<LinkedList<Integer>> list : need.values()) {
            if (list.isEmpty()) {
                continue;
            }
            for (LinkedList<Integer> list1 : list) {
                //每个list1就是一个序列
            }
        }
        return true;
    }
}
