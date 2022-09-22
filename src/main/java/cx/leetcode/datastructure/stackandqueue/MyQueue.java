package cx.leetcode.datastructure.stackandqueue;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * 232
 * https://leetcode.cn/problems/implement-queue-using-stacks/
 */
public class MyQueue {
    Deque<Integer> inQueue, outQueue;

    public MyQueue() {
        inQueue = new ArrayDeque<>();
        outQueue = new ArrayDeque<>();
    }

    public void push(int x) {
        inQueue.push(x);
    }

    //可以实现peek，也可以用于保证出队列的那个栈非空
    public int peek() {
        if (outQueue.isEmpty()) {
            while (!inQueue.isEmpty()) {
                outQueue.push(inQueue.pop());
            }
        }
        return outQueue.peek();
    }

    public int pop() {
        peek();
        return outQueue.pop();
    }

    public boolean empty() {
        return inQueue.isEmpty() && outQueue.isEmpty();
    }
}
