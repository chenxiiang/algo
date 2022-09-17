package cx.leetcode.graph.unionfind;

public class UF {
    /**
     * 连通分量的数量
     */
    private int count;

    /**
     * 存储每个节点的父节点
     */
    private int[] parent;

    /**
     * n为图中节点的个数
     *
     * @param n
     */
    public UF(int n) {
        this.count = n;
        parent = new int[n];
        //每个节点有一个指针指向父节点，根节点指向自己，初始状态每个节点都是自己的根节点
        for (int i = 0; i < n; i++) {
            parent[i] = i;
        }
    }

    public void union(int p, int q) {
        int rootP = find(p);
        int rootQ = find(q);
        //两个节点的根节点已经相等，直接返回
        if (rootP == rootQ) {
            return;
        }
        parent[rootQ] = rootP;
        count--;
    }

    //判断节点p和节点q是否连通，也就是看他们的根节点是否相同
    public boolean connected(int p, int q) {
        int rootP = find(p);
        int rootQ = find(q);
        return rootQ == rootP;
    }

    //寻找节点的根节点，使用路径压缩，在极端情况下把一棵很高的树压缩到很低
    //把节点到根节点路径上的所有节点的父节点都设置为根节点
    public int find(int x) {
        if (parent[x] != x) {
            parent[x] = find(parent[x]);
        }
        return parent[x];
    }

    /**
     * 返回图中的连通分量的个数
     */
    public int count() {
        return count;
    }
}
