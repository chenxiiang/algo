package cx.leetcode.graph.unionfind;

public class Solution990 {
    public boolean equationsPossible(String[] equations) {
        UF uf = new UF(26);
        //先让相等的字母形成连通分量
        for (String eq : equations) {
            if (eq.charAt(1) == '=') {
                char x = eq.charAt(0);
                char y = eq.charAt(3);
                uf.union(x - 'a', y - 'a');
            }
        }
        //检查不等关系是否打破相等关系的连通性
        for (String eq : equations) {
            if (eq.charAt(1) == '!') {
                char x = eq.charAt(0);
                char y = eq.charAt(3);
                //如果在相等的连通分量中，这两个不等的还是能连通，那就是逻辑冲突
                if (uf.connected(x - 'a', y - 'a')) {
                    return false;
                }
            }
        }
        return true;
    }
}
