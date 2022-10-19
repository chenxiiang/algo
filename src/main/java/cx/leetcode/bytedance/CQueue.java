package cx.leetcode.bytedance;

import java.util.ArrayDeque;
import java.util.Deque;

public class CQueue {
    Deque<Integer> in = new ArrayDeque<>();

    Deque<Integer> out = new ArrayDeque<>();

    public CQueue() {
    }

    public void appendTail(int value) {
        in.push(value);
    }

    public int deleteHead() {
        if (out.isEmpty()) {
            while (!in.isEmpty()) {
                out.push(in.pop());
            }
        }
        return out.isEmpty() ? -1 : out.pop();
    }
}
