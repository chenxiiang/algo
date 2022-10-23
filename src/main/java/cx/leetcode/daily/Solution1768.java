package cx.leetcode.daily;

public class Solution1768 {
    public String mergeAlternately(String word1, String word2) {
        int m = word1.length(), n = word2.length();
        int i = 0, j = 0;
        StringBuilder sb = new StringBuilder();
        while (i < m || j < n) {
            if (i < m) {
                sb.append(word1.charAt(i++));
            }
            if (j < n) {
                sb.append(word2.charAt(j++));
            }
        }
        return sb.toString();
    }
}
