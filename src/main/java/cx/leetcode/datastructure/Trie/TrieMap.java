package cx.leetcode.datastructure.Trie;

import java.util.ArrayList;
import java.util.List;

public class TrieMap<V> {
    //ascii码个数，实际应用中可以改为需要的字符数
    private static final int R = 256;

    //当前在map中存在的键值对个数
    private int size = 0;

    private TrieNode<V> root = null;

    private static class TrieNode<V> {
        V val = null;

        TrieNode<V>[] children = new TrieNode[256];
    }

    /***** 增/改 *****/

    // 在 Map 中添加 key
    public void put(String key, V val) {
        if (!containsKey(key)) {
            size++;
        }
        root = put(root, key, val, 0);
    }

    // 定义：向以 node 为根的 Trie 树中插入 key[i..]，返回插入完成后的根节点
    private TrieNode<V> put(TrieNode<V> node, String key, V val, int i) {
        if (node == null) {
            node = new TrieNode<>();
        }
        if (i == key.length()) {
            //key的路径已经插入完成，将值存入节点
            node.val = val;
            return node;
        }
        char c = key.charAt(i);
        node.children[c] = put(node.children[c], key, val, i + 1);
        return node;
    }

    /***** 删 *****/

    // 删除键 key 以及对应的值
    public void remove(String key) {
        if (!containsKey(key)) {
            return;
        }
        root = remove(root, key, 0);
    }

    // 定义：在以 node 为根的 Trie 树中删除 key[i..]，返回删除后的根节点
    private TrieNode<V> remove(TrieNode<V> node, String key, int i) {
        if (node == null) {
            return null;
        }
        if (i == key.length()) {
            //找到了key对应的trienode，删除val
            node.val = null;
        } else {
            char c = key.charAt(i);
            node.children[c] = remove(node.children[c], key, i + 1);
        }
        //删除一个key要看自己的val是否为空以及自己的children数组是否全部为空指针
        //如果val字段为空，说明没有存储值，如果同时children数组全部是空指针，说明自己下面也没有接树枝，即不是任何一个键的前缀，这样就可以删除

        //后序位置，递归路径上的节点可能需要清理
        if (node.val != null) {
            //如果节点存储值不能被清理
            return node;
        }

        //检查该trienode是否还有后缀
        for (int c = 0; c < R; c++) {
            if (node.children[c] != null) {
                //只要存在一个子节点，即后缀，就不需要被清理
                return node;
            }
        }
        //既没有存储val，也没有后缀，则需要被清理
        return null;
    }

    /***** 查 *****/

    // 搜索 key 对应的值，不存在则返回 null
    // get("the") -> 4
    // get("tha") -> null
    public V get(String key) {
        TrieNode<V> x = getNode(root, key);
        if (x == null || x.val == null) {
            return null;
        }
        return x.val;
    }

    // 判断 key 是否存在在 Map 中
    // containsKey("tea") -> false
    // containsKey("team") -> true
    public boolean containsKey(String key) {
        return get(key) != null;
    }

    // 在 Map 的所有键中搜索 query 的最短前缀
    // shortestPrefixOf("themxyz") -> "the"
    public String shortestPrefixOf(String query) {
        TrieNode<V> p = root;
        for (int i = 0; i < query.length(); i++) {
            if (p == null) {
                return "";
            }
            if (p.val != null) {
                //这里为啥不是i+1??
                return query.substring(0, i);
            }
            char c = query.charAt(i);
            p = p.children[c];
        }
        if (p != null && p.val != null) {
            //query本身是一个键
            return query;
        }
        return "";
    }

    // 在 Map 的所有键中搜索 query 的最长前缀
    // longestPrefixOf("themxyz") -> "them"
    public String longestPrefixOf(String query) {
        TrieNode<V> p = root;
        int maxLen = 0;
        for (int i = 0; i < query.length(); i++) {
            if (p == null) {
                break;
            }
            if (p.val != null) {
                //先记录一下，然后继续更新前缀的最大长度
                maxLen = i;
            }
            char c = query.charAt(i);
            p = p.children[c];
        }
        if (p != null && p.val != null) {
            return query;
        }
        return query.substring(0, maxLen);
    }

    // 搜索所有前缀为 prefix 的键
    // keysWithPrefix("th") -> ["that", "the", "them"]
    public List<String> keysWithPrefix(String prefix) {
        List<String> res = new ArrayList<>();
        //找到匹配prefix在trie树中的节点
        TrieNode<V> x = getNode(root, prefix);
        if (x == null) {
            return res;
        }
        traverse(x, new StringBuilder(prefix), res);
        return res;
    }

    private void traverse(TrieNode<V> node, StringBuilder path, List<String> res) {
        if (node == null) {
            //到达叶子节点
            return;
        }
        if (node.val != null) {
            //找到一个key，添加到结果
            res.add(path.toString());
        }
        for (char i = 0; i < R; i++) {
            //做选择
            path.append(i);
            traverse(node.children[i], path, res);
            //撤销选择
            path.deleteCharAt(path.length() - 1);
        }
    }

    // 判断是和否存在前缀为 prefix 的键
    // hasKeyWithPrefix("tha") -> true
    // hasKeyWithPrefix("apple") -> false
    public boolean hasKeyWithPrefix(String prefix) {
        return getNode(root, prefix) != null;
    }

    // 通配符 . 匹配任意字符，搜索所有匹配的键
    // keysWithPattern("t.a.") -> ["team", "that"]
    public List<String> keysWithPattern(String pattern) {
        List<String> res = new ArrayList<>();
        traverse(root, new StringBuilder(), pattern, 0, res);
        return res;
    }

    private void traverse(TrieNode<V> node, StringBuilder path, String pattern, int i, List<String> res) {
        if (node == null) {
            // 树枝不存在，即字符 pattern[i-1] 匹配失败
            return;
        }
        if (i == pattern.length()) {
            // pattern 匹配完成
            if (node.val != null) {
                // 如果这个节点存储着 val，则找到一个匹配的键
                res.add(path.toString());
            }
            return;
        }
        char c = pattern.charAt(i);
        if (c == '.') {
            // pattern[i] 是通配符，可以变化成任意字符
            // 多叉树（回溯算法）遍历框架
            for (char j = 0; j < R; j++) {
                path.append(j);
                traverse(node.children[j], path, pattern, i + 1, res);
                path.deleteCharAt(path.length() - 1);
            }
        } else {
            path.append(c);
            traverse(node.children[c], path, pattern, i + 1, res);
            path.deleteCharAt(path.length() - 1);
        }
    }

    // 通配符 . 匹配任意字符，判断是否存在匹配的键
    // hasKeyWithPattern(".ip") -> true
    // hasKeyWithPattern(".i") -> false
    public boolean hasKeyWithPattern(String pattern) {
        return hasKeyWithPattern(root, pattern, 0);
    }

    // 函数定义：从 node 节点开始匹配 pattern[i..]，返回是否成功匹配
    private boolean hasKeyWithPattern(TrieNode<V> node, String pattern, int i) {
        if (node == null) {
            return false;
        }

        if (i == pattern.length()) {
            //树枝不存在，即匹配失败
            return node.val != null;
        }
        char c = pattern.charAt(i);
        if (c != '.') {
            return hasKeyWithPattern(node.children[c], pattern, i + 1);
        }
        for (int j = 0; j < R; j++) {
            if (hasKeyWithPattern(node.children[j], pattern, i + 1)) {
                return true;
            }
        }
        return false;
    }

    // 从节点 node 开始搜索 key，如果存在返回对应节点，否则返回 null
    private TrieNode<V> getNode(TrieNode<V> node, String key) {
        TrieNode<V> p = node;
        for (int i = 0; i < key.length(); i++) {
            if (p == null) {
                return null;
            }
            char c = key.charAt(i);
            p = p.children[c];
        }
        return p;
    }

    // 返回 Map 中键值对的数量
    public int size() {
        return size;
    }
}
