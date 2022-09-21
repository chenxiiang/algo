package cx.leetcode.datastructure.Trie;

public class Solution211 {
    static class TrieNode {
        boolean isWord;
        TrieNode[] children = new TrieNode[26];
    }

    TrieNode root;

    public Solution211() {
        root = new TrieNode();
    }

    public void addWord(String word) {
        TrieNode p = root;
        for (int i = 0; i < word.length(); i++) {
            int idx = word.charAt(i) - 'a';
            if (p.children[idx] == null) {
                p.children[idx] = new TrieNode();
            }
            p = p.children[idx];
        }
        p.isWord = true;
    }

    public boolean search(String word) {
        //涉及到通配符的或者是让把某前缀所有的字符串都求出来这种不能在中间根据某一个节点的状态直接返回值的都需要用加上index遍历一次
        return search(root, word, 0);
    }

    public boolean search(TrieNode node, String word, int index) {
        if (node == null) {
            return false;
        }
        if (index == word.length()) {
            return node.isWord;
        }
        char c = word.charAt(index);
        if (c == '.') {
            for (int i = 0; i < 26; i++) {
                if (search(node.children[i], word, index + 1)) {
                    return true;
                }
            }
            return false;
        } else {
            return search(node.children[c - 'a'], word, index + 1);
        }
    }
}
