package cx.leetcode.datastructure;

import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Map;

public class LFU460 {
    int cap;

    Map<Integer, Integer> key2Val;

    Map<Integer, Integer> key2Freq;

    Map<Integer, LinkedHashSet<Integer>> freq2Keys;

    /**
     * 将 freq 最小的 key 删除，那你就得快速得到当前所有 key 最小的 freq 是多少。
     * 想要时间复杂度 O(1) 的话，肯定不能遍历一遍去找，那就用一个变量 minFreq 来记录当前最小的 freq。
     */
    int minFreq;

    // 构造容量为 capacity 的缓存
    public LFU460(int capacity) {
        this.cap = capacity;
        this.minFreq = 0;
        key2Val = new HashMap<>();
        key2Freq = new HashMap<>();
        freq2Keys = new HashMap<>();
    }

    // 在缓存中查询 key
    public int get(int key) {
        if (!key2Val.containsKey(key)) {
            return -1;
        }
        //增加key对应的freq
        increaseFreq(key);
        return key2Val.get(key);
    }

    // 将 key 和 val 存入缓存
    public void put(int key, int val) {
        if (this.cap <= 0) {
            return;
        }
        if (key2Val.containsKey(key)) {
            key2Val.put(key, val);
            increaseFreq(key);
            return;
        }
        //key不存在
        //容量已满的需要淘汰一个freq最小的key
        if (this.cap <= key2Val.size()) {
            removeMinFreq();
        }

        //插入key和val，对应的freq为1
        key2Val.put(key, val);
        key2Freq.put(key, 1);
        freq2Keys.putIfAbsent(1, new LinkedHashSet<>());
        freq2Keys.get(1).add(key);
        this.minFreq = 1;
    }

    private void removeMinFreq() {
        LinkedHashSet<Integer> keys = freq2Keys.get(this.minFreq);
        //相同freq的key应该删除最先插入的，也就是最旧的，也就是set的第一个元素
        int deletedKey = keys.iterator().next();
        keys.remove(deletedKey);
        if (keys.isEmpty()) {
            freq2Keys.remove(this.minFreq);
            // 这里不需要更新minfFreq，首先，如果无法快速计算，只能线性遍历fk或者kf表来计算，无法保证O(1)的时间复杂度；
            // 另外，也没有必要更新它，因为removeMinFreq只在put时调用，而put时，一定会把minFreq更新为1
        }
        key2Val.remove(deletedKey);
        key2Freq.remove(deletedKey);
    }

    private void increaseFreq(int key) {
        int freq = key2Freq.get(key);
        key2Freq.put(key, freq + 1);
        freq2Keys.get(freq).remove(key);
        freq2Keys.putIfAbsent(freq + 1, new LinkedHashSet<>());
        freq2Keys.get(freq + 1).add(key);
        if (freq2Keys.get(freq).isEmpty()) {
            freq2Keys.remove(freq);
            if (freq == this.minFreq) {
                this.minFreq++;
            }
        }
    }
}
