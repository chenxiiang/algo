package cx.leetcode.bytedance;

public class Solution6 {
    public String convert(String s, int numRows) {
        if (numRows == 1) {
            return s;
        }
        StringBuilder res = new StringBuilder();
        int i = 0, dir = -1;
        StringBuilder[] rows = new StringBuilder[numRows];
        for (int m = 0; m < numRows; m++) {
            rows[m] = new StringBuilder();
        }
        for (char c : s.toCharArray()) {
            rows[i].append(c);
            if (i == 0 || i == numRows - 1) {
                dir = -dir;
            }
            i += dir;
        }
        for (StringBuilder sb : rows) {
            res.append(sb);
        }
        return res.toString();
    }
}
