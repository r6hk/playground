package dev.rennen.template;

/**
 * @author rennen.dev
 * @date 2024/9/15 16:49
 */
public class HandWriteHashMap<K, V> {

    private static class Node<K, V> {
        K key;
        V value;
        Node<K, V> next;

        public Node(K key, V value, Node<K, V> next) {
            this.key = key;
            this.value = value;
            this.next = next;
        }

        public Node(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }

    // 默认容量
    final int DEFAULT_CAPACITY = 16;

    // 负载因子
    final float LOAD_FACTOR = 0.75f;

    // 当前大小
    private int size;

    // 桶数组
    Node<K, V>[] buckets;

    /**
     * 无参构造器
     */
    @SuppressWarnings("unchecked")
    public HandWriteHashMap() {
        this.size = 0;
        this.buckets = new Node[DEFAULT_CAPACITY];
    }

    /**
     * 有参构造器
     */
    @SuppressWarnings("unchecked")
    public HandWriteHashMap(int capacity) {
        this.size = 0;
        this.buckets = new Node[capacity];
    }

    public V get(K key) {
        int index = getIndex(key, buckets.length);
        Node<K, V> node = buckets[index];
        if (node == null) return null;
        while (node != null) {
            if (key.hashCode() == node.key.hashCode() && (node.key == key || node.key.equals(key))) {
                return node.value;
            }
            node = node.next;
        }
        return null;
    }

    private int getIndex(K key, int length) {
        return Math.abs(key.hashCode() % length);
    }

    public void put(K key, V value) {
        if (size >= buckets.length * LOAD_FACTOR) resize();
        putVal(key, value, buckets);
    }

    private void putVal(K key, V value, Node<K, V>[] buckets) {
        int index = getIndex(key, buckets.length);
        Node<K, V> existNode = buckets[index];
        if (existNode == null) {
            buckets[index] = new Node<>(key, value);
            size++;
            return;
        }
        while (existNode != null) {
            if (existNode.hashCode() == key.hashCode() &&
                    (existNode.key == key || existNode.key.equals(key))) {
                existNode.value = value;
                return;
            }
            existNode = existNode.next;
        }
        Node<K, V> newNode = new Node<>(key, value, buckets[index]);
        buckets[index] = newNode;
        size++;
    }

    private void resize() {
        Node<K, V>[] newBucket = new Node[buckets.length * 2];
        rehash(newBucket);
        buckets = newBucket;
    }

    private void rehash(Node<K, V>[] newBucket) {
        for (Node<K, V> kvNode : newBucket) {
            while (kvNode != null) {
                putVal(kvNode.key, kvNode.value, newBucket);
                kvNode = kvNode.next;
            }
        }
    }
}
