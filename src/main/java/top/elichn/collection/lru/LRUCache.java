package top.elichn.collection.lru;

import java.util.Map;

/**
 * <p>Title: LRUCache</p>
 * <p>Description: LRUCache</p>
 *
 * @author elichn
 * @version 1.0
 * @date 2020/1/6
 */
public class LRUCache {
    private int capacity;
    private Map<Integer, Integer> cache;

    public LRUCache(int capacity) {
        this.capacity = capacity;
        this.cache = new java.util.LinkedHashMap<Integer, Integer>(capacity, 0.75f, true) {
            // 定义put后的移除规则，大于容量就删除eldest
            @Override
            protected boolean removeEldestEntry(Map.Entry<Integer, Integer> eldest) {
                return size() > capacity;
            }
        };
    }

    public int get(int key) {
        if (cache.containsKey(key)) {
            return cache.get(key);
        } else {
            return -1;
        }
    }

    public void set(int key, int value) {
        cache.put(key, value);
    }
}
