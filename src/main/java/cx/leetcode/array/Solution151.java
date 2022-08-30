package cx.leetcode.array;

public class Solution151 {
    public String reverseWords(String s) {
        StringBuilder sb = new StringBuilder();
        s = s.trim();
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            if (ch != ' ') {
                sb.append(ch);
            } else if (sb.charAt(sb.length() - 1) != ' ') {
                sb.append(ch);
            }
        }
        char[] chars = sb.toString().toCharArray();
        int n = chars.length;
        reverseString(chars, 0, n - 1);
        for (int i = 0; i < n; ) {
            for (int j = i; j < n; j++) {
                if (j + 1 == n || chars[j + 1] == ' ') {
                    reverseString(chars, i, j);
                    i = j + 2;
                    break;
                }
            }
        }
        return new String(chars);
    }

    private void reverseString(char[] chars, int i, int j) {
        while (i < j) {
            char temp = chars[i];
            chars[i] = chars[j];
            chars[j] = temp;
            i++;
            j--;
        }
    }
}
