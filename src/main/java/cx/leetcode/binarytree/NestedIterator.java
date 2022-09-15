package cx.leetcode.binarytree;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class NestedIterator implements Iterator<Integer> {
    private LinkedList<NestedInteger> list;

    public NestedIterator(List<NestedInteger> nestedList) {
        list = new LinkedList<>(nestedList);
    }

    @Override
    public boolean hasNext() {
        //循环拆分列表元素，直到第一个元素是整数
        while (!list.isEmpty() && !list.get(0).isInteger()) {
            //第一个元素是列表
            List<NestedInteger> first = list.remove(0).getList();
            //将第一个元素打开并按顺序添加到开头
            for (int i = first.size() - 1; i >= 0; i--) {
                list.addFirst(first.get(i));
            }
        }
        return !list.isEmpty();
    }

    @Override
    public Integer next() {
        //每次next之前都会调用hasNext，保证第一个元素一定是整数
        return list.remove(0).getInteger();
    }

    interface NestedInteger {

        // @return true if this NestedInteger holds a single integer, rather than a nested list.
        public boolean isInteger();

        // @return the single integer that this NestedInteger holds, if it holds a single integer
        // Return null if this NestedInteger holds a nested list
        public Integer getInteger();

        // @return the nested list that this NestedInteger holds, if it holds a nested list
        // Return empty list if this NestedInteger holds a single integer
        public List<NestedInteger> getList();
    }
}
