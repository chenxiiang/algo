package cx.leetcode.datastructure.Trie;

import java.util.HashMap;
import java.util.Map;

public class Solution677 {

    static class TrieNode {
        int val = 0;

        TrieNode[] children = new TrieNode[26];
    }

    TrieNode root;

    Map<String, Integer> map;

    public Solution677() {
        root = new TrieNode();
        map = new HashMap<>();
    }

    public void insert(String key, int val) {
        TrieNode p = root;
        int delta = val - map.getOrDefault(key, 0);
        map.put(key, val);
        for (char c : key.toCharArray()) {
            int idx = c - 'a';
            if (p.children[idx] == null) {
                p.children[idx] = new TrieNode();
            }
            p = p.children[idx];
            p.val += delta;
        }
    }

    public int sum(String prefix) {
        TrieNode p = root;
        for (char c : prefix.toCharArray()) {
            int idx = c - 'a';
            if (p.children[idx] == null) {
                return 0;
            }
            p = p.children[idx];
        }
        return p.val;
    }
}
