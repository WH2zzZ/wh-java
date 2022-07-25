package leetcode.lru;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

class LRUJavaCache extends LinkedHashMap<Integer, Integer>{

    private int capacity;

    public LRUJavaCache(int initialCapacity) {
        super(initialCapacity, 0.75f, true);
        capacity = initialCapacity;
    }

    public int get(int key) {
        return super.getOrDefault(key, -1);
    }
    
    public void put(int key, int value) {
        super.put(key, value);
    }

    @Override
    protected boolean removeEldestEntry(Map.Entry<Integer, Integer> eldest) {
        return size() > capacity;
    }

    public static void main(String[] args) {
        System.out.println(1 >>> 16);
        HashMap<Integer, Integer> hashMap = new HashMap<>(4);
        for (int i = 0; i < 4; i++) {
            hashMap.put(i, i);
        }
    }
}