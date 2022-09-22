package cx.leetcode.datastructure.dandiaoduilie;

import java.util.ArrayDeque;
import java.util.Deque;

public class Solution239 {
    //结合这个方法可以更好的理解下面的写法，思路是完全一样的，但是下面的重复代码更少
    public int[] maxSlidingWindow(int[] nums, int k) {
        int n = nums.length;
        Deque<Integer> stack = new ArrayDeque<>();
        for (int i = 0; i < k; i++) {
            while (!stack.isEmpty() && nums[i] >= nums[stack.peekLast()]) {
                stack.pollLast();
            }
            stack.addLast(i);
        }

        int[] ans = new int[n - k + 1];
        ans[0] = nums[stack.peekFirst()];
        for (int i = k; i < n; i++) {
            while (!stack.isEmpty() && nums[i] >= nums[stack.peekLast()]) {
                stack.pollLast();
            }
            stack.addLast(i);
            while (stack.peekFirst() <= i - k) {
                stack.pollFirst();
            }
            ans[i - k + 1] = nums[stack.peekFirst()];
        }
        return ans;
    }

    public int[] maxSlidingWindow1(int[] nums, int k) {
        Deque<Integer> deque = new ArrayDeque<>();
        int n = nums.length, m = n - k + 1;
        int[] ans = new int[m];
        for (int i = 0; i < n; i++) {
            while (!deque.isEmpty() && nums[i] >= nums[deque.peekLast()]) {
                deque.pollLast();
            }
            deque.addLast(i);
            //i >= k - 1 说明第一个窗口已经填满，数组的第一个值已经得到
            if (i >= k - 1) {
                //i - k >= deque.peekFirst() 说明上一个窗口的最大值是它的最左边的值，现在要移除窗口了
                //如果不满足这个条件的说明不是窗口最左的值，在当前窗口中还可以使用
                while (!deque.isEmpty() && i - k >= deque.peekFirst()) {
                    deque.pollFirst();
                }
                ans[i - k + 1] = nums[deque.peekFirst()];
            }
        }
        return ans;
    }
}
