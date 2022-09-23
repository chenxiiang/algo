package cx.leetcode.datastructure.stackandqueue;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 225
 * https://leetcode.cn/problems/implement-stack-using-queues/
 */
public class MyStack {
    Queue<Integer> queue = new LinkedList<>();

    int topElement = 0;

    public MyStack() {

    }

    public void push(int x) {
        topElement = x;
        queue.offer(x);
    }

    public int pop() {
        if (queue.isEmpty()) {
            return -1;
        }
        //不能这样判断是否把需要的已经放到队头，因为可能重复
        // while (queue.peek() != topElement) {
        // }
        int size = queue.size();
        while (size > 2) {
            queue.offer(queue.poll());
            size--;
        }
        //size到2再停止是为了更新顶端元素，否则只是把之前的头部元素弹出去了，但是无法获取到后面的那个元素
        topElement = queue.peek();
        queue.offer(queue.poll());

        return queue.poll();
    }

    public int top() {
        return topElement;
    }

    public boolean empty() {
        return queue.isEmpty();
    }
}
