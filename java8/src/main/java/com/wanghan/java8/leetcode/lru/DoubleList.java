package com.wanghan.java8.leetcode.lru;

/**
 * @Author WangHan
 * @Create 2020/5/25 10:31 上午
 */
public class DoubleList {

    /**
     * 头尾虚节点
     */
    private Node head;
    private Node tail;

    public DoubleList() {
        this.head = new Node(0, 0);
        this.tail = new Node(0, 0);
    }

    class Node{
        private Node next;
        private Node prev;
        private Integer value;
        private Integer key;

        public Node(Integer key, Integer value) {
            this.value = value;
            this.key = key;
        }

        public Integer getKey() {
            return key;
        }

        public void setKey(Integer key) {
            this.key = key;
        }

        public Integer getValue() {
            return value;
        }

        public void setValue(Integer value) {
            this.value = value;
        }
    }

    public Node addFirst(Integer key, Integer value){
        Node node = new Node(key, value);
        if (head.next == null && tail.prev == null){
            head.next = node;
            node.prev = head;

            tail.prev = node;
            node.next = tail;
            return node;
        }

        Node next = head.next;
        next.prev = node;
        head.next = node;
        node.next = next;
        node.prev = head;
        return node;
    }

    public void toHead(Node node){
        if (head.next == null){
            head.next = node;
            node.prev = head;
        }

        removeNode(node);
        node.prev = head;
        node.next = head.next;
        node.next.prev = node;
        head.next = node;
    }

    public void removeLast(){
        if (tail.prev == null){
            return;
        }
        removeNode(tail.prev);
    }

    public void removeNode(Node node){
        Node next = node.next;
        Node prev = node.prev;

        next.prev = prev;
        prev.next = next;
    }

    public Node getLast(){
        return tail.prev;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        Node next = head.next;
        while (next != tail){
            result.append(next.value).append(" ");
            next = next.next;
        }

        return String.valueOf(result);
    }
}
