package cx.leetcode.bytedance;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * 重点
 */
public class Solution93 {
    List<String> res = new ArrayList<>();
    LinkedList<String> track = new LinkedList<>();

    public List<String> restoreIpAddresses(String s) {
        int len = s.length();
        if (len > 12 || len < 4) {
            return res;
        }
        dfs(s, len, 0, 4);
        return res;
    }

    //rest记录还有多少个段没分割，ip地址一共4个段
    private void dfs(String s, int len, int start, int rest) {
        if (start == len) {
            if (rest == 0) {
                res.add(String.join(".", track));
            }
            return;
        }
        for (int i = start; i < start + 3; i++) {
            if (i >= len) {
                break;
            }
            //如果剩下的段每个都3位还是用不完剩下的位数，跳过当前选择
            if (rest * 3 < len - i) {
                continue;
            }
            if (judgeIpSegment(s, start, i)) {
                String currSegment = s.substring(start, i + 1);
                track.add(currSegment);
                dfs(s, len, i + 1, rest - 1);
                track.removeLast();
            }
        }
    }

    private boolean judgeIpSegment(String s, int left, int right) {
        int len = right - left + 1;
        if (len > 1 && s.charAt(left) == '0') {
            return false;
        }
        int res = 0;
        while (left <= right) {
            res = res * 10 + s.charAt(left) - '0';
            left++;
        }
        return res >= 0 && res <= 255;
    }
}
