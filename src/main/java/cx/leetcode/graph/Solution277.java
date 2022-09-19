package cx.leetcode.graph;

public class Solution277 {
    //根据名人的定义，人群中最多有一个名人
    public int findCelebrity(int n) {
        //假设cand是名人
        int cand = 0;
        for (int other = 1; other < n; other++) {
            if (knows(cand, other) || !knows(other, cand)) {
                //cand不是名人，排除，假设other是名人
                cand = other;
            } else {
                //other更不是名人，继续假设cand是名人
            }
        }
        //现在的cand是排除的最后结果，但是不保证一定是名人，进行最后的检查
        for (int other = 0; other < n; other++) {
            if (cand == other) {
                continue;
            }
            if (knows(cand, other) || !knows(other, cand)) {
                return -1;
            }
        }
        return cand;
    }

    //这是题目给的接口，这里定义只是为了编译通过
    private boolean knows(int a, int b) {
        return true;
    }
}
