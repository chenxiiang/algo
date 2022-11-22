package cx.leetcode.bytedance;

public class Solution43 {
    public String multiply(String num1, String num2) {
        int m = num1.length(), n = num2.length();
        char[] chars1 = num1.toCharArray(), chars2 = num2.toCharArray();
        int[] res = new int[m + n];
        for (int i = m - 1; i >= 0; i--) {
            for (int j = n - 1; j >= 0; j--) {
                int mul = (chars1[i] - '0') * (chars2[j] - '0');
                int p1 = i + j, p2 = i + j + 1;
                int sum = mul + res[p2];
                //不满10的部分
                res[p2] = sum % 10;
                //满的10全部进位
                res[p1] += sum / 10;
            }
        }
        int i = 0;
        while (i < res.length && res[i] == 0) {
            i++;
        }
        StringBuilder sb = new StringBuilder();
        for (; i < res.length; i++) {
            sb.append(res[i]);
        }
        return sb.length() == 0 ? "0" : sb.toString();
    }
}
