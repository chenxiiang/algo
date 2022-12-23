/*
 * Copyright (c) Huawei Technologies Co., Ltd. 2022-2022. All rights reserved.
 */

package cx.leetcode.daily.trie;

import java.util.HashMap;
import java.util.Map;

/**
 * 功能描述
 *
 * @author c00575945
 * @since 2022-12-23
 */
public class Trie {
    class TrieNode {
        boolean end;

        int count;

        TrieNode[] children = new TrieNode[26];
    }

    TrieNode root;

    Map<String, Integer> countMap;

    public Trie() {
        root = new TrieNode();
        countMap = new HashMap<>();
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
        p.end = true;
        countMap.put(word, countMap.getOrDefault(word, 0) + 1);
    }

    public int countWordsEqualTo(String word) {
        return countMap.getOrDefault(word, 0);
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
        if (!countMap.containsKey(word)) {
            return;
        } else {
            countMap.put(word, countMap.get(word) - 1);
        }
        for (int i = 0; i < word.length(); i++) {
            int pos = word.charAt(i) - 'a';
            p.children[pos].count--;
            p = p.children[pos];
        }
        p.end = false;
    }
}
