package cx.leetcode.bfs;

import java.util.*;

public class Solution752 {
    //一次拨动可能存在8种情况，每一位可以向上或向下，一共4位
    public int openLock(String[] deadends, String target) {
        //记录已经遍历过的，死亡数字也可以看做是遍历过的，不能加入结果的
        Set<String> visited = new HashSet<>(Arrays.asList(deadends));
        Queue<String> q = new ArrayDeque<>();
        int step = 0;
        if (!visited.contains("0000")) {
            q.offer("0000");
        }
        // visited.add("0000");
        while (!q.isEmpty()) {
            int size = q.size();
            for (int i = 0; i < size; i++) {
                String curr = q.poll();
                if (curr.equals(target)) {
                    return step;
                }
                //对当前的密码每一位进行拨动
                for (int j = 0; j < 4; j++) {
                    String up = plusOne(curr, j);
                    if (!visited.contains(up)) {
                        q.offer(up);
                        visited.add(up);
                    }
                    String down = minusOne(curr, j);
                    if (!visited.contains(down)) {
                        q.offer(down);
                        visited.add(down);
                    }
                }
            }
            step++;
        }
        return -1;
    }

    //将s[i]向上拨动一次
    String plusOne(String s, int i) {
        char[] ch = s.toCharArray();
        if (ch[i] == '9') {
            ch[i] = '0';
        } else {
            ch[i] += 1;
        }
        return new String(ch);
    }

    //将s[i]向下拨动一次
    String minusOne(String s, int i) {
        char[] ch = s.toCharArray();
        if (ch[i] == '0') {
            ch[i] = '9';
        } else {
            ch[i] -= 1;
        }
        return new String(ch);
    }
}
