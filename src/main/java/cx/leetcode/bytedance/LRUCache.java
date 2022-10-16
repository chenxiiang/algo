package cx.leetcode.bytedance;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class LRUCache {
    int cap;
    Map<Integer, Integer> cache = new LinkedHashMap<>();

    public LRUCache(int capacity) {
        this.cap = capacity;
    }

    public int get(int key) {
        if (!cache.containsKey(key)) {
            return -1;
        }
        makeRecently(key);
        return cache.get(key);
    }

    public void put(int key, int value) {
        if (cache.containsKey(key)) {
            cache.put(key, value);
            makeRecently(key);
            return;
        }
        if (cache.size() >= this.cap) {
            int oldest = cache.keySet().iterator().next();
            cache.remove(oldest);
        }
        cache.put(key, value);
    }

    private void makeRecently(int key) {
        int val = cache.get(key);
        cache.remove(key);
        cache.put(key, val);
    }
}

class LRUCache1 {
    class Node {
        public int key, val;
        public Node prev, next;

        public Node() {

        }

        public Node(int key, int val) {
            this.key = key;
            this.val = val;
        }
    }

    private Map<Integer, Node> cache = new HashMap<>();
    private int cap;
    private Node head, tail;

    public LRUCache1(int capacity) {
        this.cap = capacity;
        this.head = new Node();
        this.tail = new Node();
        head.next = tail;
        tail.prev = head;
    }

    public int get(int key) {
        Node node = cache.get(key);
        if (node == null) {
            return -1;
        }
        moveToHead(node);
        return node.val;
    }

    public void put(int key, int value) {
        Node node = cache.get(key);
        if (node == null) {
            Node newNode = new Node(key, value);
            cache.put(key, newNode);
            addToHead(newNode);
            if (cache.size() > cap) {
                Node tail = removeTail();
                cache.remove(tail.key);
            }
        } else {
            node.val = value;
            moveToHead(node);
        }
    }

    //将头部节点作为最近访问的节点
    private void addToHead(Node x) {
        x.prev = head;
        x.next = head.next;
        head.next.prev = x;
        head.next = x;
    }

    private void removeNode(Node x) {
        x.prev.next = x.next;
        x.next.prev = x.prev;
    }

    private void moveToHead(Node x) {
        removeNode(x);
        addToHead(x);
    }

    //tail为最久未使用
    private Node removeTail() {
        Node node = tail.prev;
        removeNode(node);
        return node;
    }
}