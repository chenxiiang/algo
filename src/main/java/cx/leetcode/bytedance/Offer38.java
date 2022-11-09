package cx.leetcode.bytedance;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Offer38 {
    List<String> res = new ArrayList<>();
    StringBuilder track = new StringBuilder();
    boolean[] used;

    public String[] permutation(String s) {
        char[] chars = s.toCharArray();
        Arrays.sort(chars);
        used = new boolean[s.length()];
        backtrack(chars);
        return res.toArray(new String[0]);
    }

    private void backtrack(char[] chars) {
        if (track.length() == chars.length) {
            res.add(track.toString());
            return;
        }
        for (int i = 0; i < chars.length; i++) {
            if (used[i]) {
                continue;
            }
            //当前元素与上一个元素相同，上一个为false跳过的意思是如果前面的相邻相等元素没有被用过，则当前元素也不用，只要保持相同元素的相对顺序，就可以避免出现重复的结果
            if (i > 0 && chars[i] == chars[i - 1] && !used[i - 1]) {
                continue;
            }
            track.append(chars[i]);
            used[i] = true;
            backtrack(chars);
            track.deleteCharAt(track.length() - 1);
            used[i] = false;
        }
    }
}
