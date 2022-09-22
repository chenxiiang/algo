package cx.leetcode.datastructure;

import java.util.PriorityQueue;

public class Solution295 {
    PriorityQueue<Integer> large;

    PriorityQueue<Integer> small;

    public Solution295() {
        large = new PriorityQueue<>();
        small = new PriorityQueue<>((a, b) -> b - a);
    }

    public void addNum(int num) {
        if (small.size() >= large.size()) {
            small.offer(num);
            large.offer(small.poll());
        } else {
            large.offer(num);
            small.offer(large.poll());
        }
    }

    public double findMedian() {
        if (large.size() > small.size()) {
            return large.peek();
        } else if (small.size() > large.size()) {
            return small.peek();
        } else {
            return (large.peek() + small.peek()) / 2.0;
        }
    }
}
