package cx.leetcode.datastructure.Trie;

import java.util.HashMap;
import java.util.Map;

public class Solution1804 {
    static class TrieNode {
        boolean isWord;
        int count;
        TrieNode[] children = new TrieNode[26];
    }

    TrieNode root;
    Map<String, Integer> map;

    public Solution1804() {
        root = new TrieNode();
        map = new HashMap<>();
    }

    public void insert(String word) {
        TrieNode p = root;
        for (int i = 0; i < word.length(); i++) {
            int pos = word.charAt(i) - 'a';
            if (p.children[pos] == null) {
                p.children[pos] = new TrieNode();
            }
            p = p.children[pos];
            p.count++;
        }
        p.isWord = true;
        map.put(word, map.getOrDefault(word, 0) + 1);
    }

    public int countWordsEqualTo(String word) {
        return map.getOrDefault(word, 0);
    }

    public int countWordsStartingWith(String prefix) {
        TrieNode p = root;
        for (int i = 0; i < prefix.length(); i++) {
            int pos = prefix.charAt(i) - 'a';
            if (p.children[pos] == null) {
                return 0;
            }
            p = p.children[pos];
        }
        return p.count;
    }

    public void erase(String word) {
        TrieNode p = root;
        if (!map.containsKey(word)) {
            return;
        } else {
            map.put(word, map.get(word) - 1);
        }

        for (int i = 0; i < word.length(); i++) {
            int pos = word.charAt(i);
            //word不存在的情况已经用map直接返回了
            //word存在的情况，路径上的节点一定都是不为空的，直接count--也是可以的
            if (p.children[pos] != null) {
                p.children[pos].count--;
            }
            p = p.children[pos];
        }
        p.isWord = false;
    }
}
