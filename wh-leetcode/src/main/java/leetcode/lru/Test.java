package leetcode.lru;

/**
 * @Author WangHan
 * @Create 2020/5/25 11:36 上午
 */
public class Test {
    public static void main(String[] args) {
        LRUCache lruCache = new LRUCache(2);

        lruCache.put(1, 1);
        System.out.println(lruCache);
        lruCache.put(2, 2);
        System.out.println(lruCache);
        System.out.println("获取数据" + lruCache.get(1));
        lruCache.put(3, 3);
        System.out.println(lruCache);
        System.out.println("获取数据" + lruCache.get(2));
        lruCache.put(4, 4);
        System.out.println(lruCache);
        System.out.println(lruCache);
        lruCache.put(5, 5);
        System.out.println(lruCache);
        lruCache.put(6, 6);
        System.out.println(lruCache);
    }
}

