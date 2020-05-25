package com.wanghan.java8.lru;

import java.util.HashMap;

/**
 * @Author WangHan
 * @Create 2020/5/25 10:49 上午
 */
public class LRUCache {

    private int capacity;

    private HashMap<Integer, DoubleList.Node> map;

    private DoubleList doubleList;

    private int count;

    public LRUCache(int initialCapacity) {
        capacity = initialCapacity;
        this.map = new HashMap<>((int) Math.ceil(capacity / 0.75));
        doubleList = new DoubleList();
    }

    public int get(int key) {
        DoubleList.Node node = map.get(key);
        if (node == null){
            return -1;
        }
        doubleList.toHead(node);
        return node.getValue();
    }

    public void put(Integer key, int value) {
        DoubleList.Node node;
        if ((node = map.get(key)) != null){
            node.setValue(value);
            doubleList.toHead(node);
            return;
        }

        if (count == capacity){
            DoubleList.Node removeNode = doubleList.getLast();
            doubleList.removeLast();
            map.remove(removeNode.getKey());
        }else {
            count++;
        }
        node = doubleList.addFirst(key, value);
        map.put(key, node);
    }

    @Override
    public String toString() {
        return "LRUCache{" + doubleList + '}';
    }
}
