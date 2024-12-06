/**
 * @author rennen.dev
 * @date 2024/12/4 9:10
 */
public class HandWriteHashMap<K, V> {

    private static class Node<K, V> {
        K key;
        V value;
        Node<K, V> next;

        public Node (K key, V value) {
            this.key = key;
            this.value = value;
        }

        public Node (K key, V value, Node<K, V> next) {
            this.key = key;
            this.value = value;
            this.next = next;
        }
    }

    private Node<K, V>[] bucket;

    private static final int INITIAL_CAPACITY = 16;

    private int size;

    public HandWriteHashMap() {

    }

}
