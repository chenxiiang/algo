package cx.leetcode.datastructure.Trie;

import java.util.List;

public class Solution648 {
    static class TrieNode {
        boolean isWord;
        TrieNode[] children = new TrieNode[26];
    }

    TrieNode root = new TrieNode();

    public String replaceWords(List<String> dictionary, String sentence) {
        for (String str : dictionary) {
            add(str);
        }
        StringBuilder sb = new StringBuilder();
        for (String str : sentence.split(" ")) {
            sb.append(shortestPrefixOf(str)).append(" ");
        }
        return sb.substring(0, sb.length() - 1);
    }

    private String shortestPrefixOf(String query) {
        TrieNode p = root;
        for (int i = 0; i < query.length(); i++) {
            int idx = query.charAt(i) - 'a';
            if (p.children[idx] == null) {
                break;
            }
            if (p.children[idx].isWord) {
                return query.substring(0, i+1);
            }
            p = p.children[idx];
        }
        return query;
    }

    private void add(String s) {
        TrieNode p = root;
        for (int i = 0; i < s.length(); i++) {
            int idx = s.charAt(i) - 'a';
            if (p.children[idx] == null) {
                p.children[idx] = new TrieNode();
            }
            p = p.children[idx];
        }
        p.isWord = true;
    }
}
