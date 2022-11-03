package cx.leetcode.bytedance;

public class Solution8 {
    public int myAtoi(String s) {
        s = s.trim();
        if (s.length() == 0) {
            return 0;
        }
        char[] chars = s.toCharArray();
        if (!Character.isDigit(chars[0]) && chars[0] != '-' && chars[0] != '+') {
            return 0;
        }
        long ans = 0L;
        int flag = chars[0] == '-' ? -1 : 1;
        int index = !Character.isDigit(chars[0]) ? 1 : 0;
        while (index < s.length() && Character.isDigit(chars[index])) {
            ans = ans * 10 + (chars[index++] - '0');
            if (flag == 1 & ans > Integer.MAX_VALUE) {
                ans = Integer.MAX_VALUE;
                break;
            }
            if (flag == -1 && ans > 1L + Integer.MAX_VALUE) {
                ans = 1L + Integer.MAX_VALUE;
                break;
            }
        }
        return (int) (flag * ans);
    }
}
