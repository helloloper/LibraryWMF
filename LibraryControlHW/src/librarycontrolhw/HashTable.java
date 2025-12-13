public class HashTable<K, V> {
    private NodeHash<K, V>[] table;
    private int size;

    public HashTable() {
        this(10);
    }

    public HashTable(int size) {
        this.size = size;
        this.table = (NodeHash<K, V>[]) new NodeHash[size];
    }

    public void put(K key, V value) {
        int index = key.hashCode() % size;
        if (table[index] == null) {
            table[index] = new NodeHash<>(key, value);
            return;
        }

        NodeHash<K, V> current = table[index];
        NodeHash<K, V> prev = null;

        while (current != null) {
            if (current.key.equals(key)) {
                current.value = value;
                return;
            }
            prev = current;
            current = current.next;
        }
        prev.next = new NodeHash<>(key, value);
    }

    public void remove(K key) {
        int index = key.hashCode() % size;

        NodeHash<K, V> current = table[index];
        NodeHash<K, V> prev = null;

        while (current != null) {
            
            if (current.key.equals(key)) {

                if (prev == null) {
                    table[index] = current.next;
                }

                else {
                    prev.next = current.next;
                }
                return;
            }

            prev = current;
            current = current.next;
        }
    }

    public V get(K key) {
        int index = key.hashCode() % size;
        NodeHash<K, V> current = table[index];
        while (current != null) {
            if (current.key.equals(key)) {
                return current.value;
            }
            current = current.next;
        }
        return null;
    }

}

class NodeHash<K, V> {
    K key;
    V value;
    NodeHash<K, V> next;

    NodeHash(K key, V value) {
        this.key = key;
        this.value = value;
        this.next = null;
    }
}
