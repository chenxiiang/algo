package cx.leetcode.O;

public class Solution43 {
    // num1[i] 和 num2[j] 的乘积对应的就是 res[i+j] 和 res[i+j+1] 这两个位置，数组高索引位代表结果的低位
    public String multiply(String num1, String num2) {
        int m = num1.length(), n = num2.length();
        char[] chars1 = num1.toCharArray(), chars2 = num2.toCharArray();
        //结果长度最长为m+n
        int[] res = new int[m + n];
        for (int i = m - 1; i >= 0; i--) {
            for (int j = n - 1; j >= 0; j--) {
                int mul = (chars1[i] - '0') * (chars2[j] - '0');
                //数组高索引位代表结果的低位，如果有进位，则放到p1
                int p1 = i + j, p2 = i + j + 1;
                int sum = mul + res[p2];
                res[p2] = sum % 10;
                res[p1] += sum / 10;
            }
        }
        //结果数组可能填不满，前面会有0
        int i = 0;
        while (i < res.length && res[i] == 0) {
            i++;
        }
        StringBuilder str = new StringBuilder();
        for (; i < res.length; i++) {
            str.append(res[i]);
        }
        return str.length() == 0 ? "0" : str.toString();
    }
}
