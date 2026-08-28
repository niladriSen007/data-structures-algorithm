package data_structures.hashmap;

public class MyHashMap<K, V> {
    class Node<K, V> {
        K key;
        V value;
        Node<K, V> next;

        public Node(K key, V value) {
            this.key = key;
            this.value = value;
            this.next = null;
        }
    }

    private Node<K, V>[] buckets;
    private int size;
    private static final int DEFAULT_CAPACITY = 16;
    private static final float LOAD_FACTOR = .75f;

    public MyHashMap() {
        this.buckets = new Node[DEFAULT_CAPACITY];
    }

    /**
     * PUT - operation
     */
    public void put(K key, V value) {
        int bucketIndex = getBucketIndex(key);

        Node<K, V> targetNode = buckets[bucketIndex];

        // check wheather the key already exists or not
        while (targetNode != null) {
            if (keyEquality(targetNode.key, key)) {
                targetNode.value = value;
                return;
            }
            targetNode = targetNode.next;
        }

        // ket does not exist so insert key as new node
        Node<K, V> newNode = new Node<>(key, value);
        newNode.next = buckets[bucketIndex];
        buckets[bucketIndex] = newNode;

        size++;

        if ((float) (size / DEFAULT_CAPACITY) > LOAD_FACTOR)
            resize();

    }

    /**
     * GET - opeeration
     */
    public V get(K key) {
        int bucketIndex = getBucketIndex(key);
        Node<K, V> targetNode = buckets[bucketIndex];
        while (targetNode != null) {
            if (keyEquality(targetNode.key, key)) {
                return targetNode.value;
            }
            targetNode = targetNode.next;
        }
        return null;
    }

    /**
     * REMOVE - operation
     */
    public V remove(K key) {
        int bucketIndex = getBucketIndex(key);
        Node<K, V> targetNode = buckets[bucketIndex];
        Node<K, V> previous = null;
        while (targetNode != null) {
            if (keyEquality(targetNode.key, key)) {
                if (previous == null)
                    buckets[bucketIndex] = targetNode.next;
                else {
                    previous.next = targetNode.next;
                }
                size--;
                return targetNode.value;
            }
            previous = targetNode;
            targetNode = targetNode.next;
        }
        return null;
    }

    /**
     * CONTAINS_KEY - operation
     */
    public boolean containsKey(K key) {
        int bucketIndex = getBucketIndex(key);
        Node<K, V> targetNode = buckets[bucketIndex];
        while (targetNode != null) {
            if (keyEquality(targetNode.key, key)) {
                return true;
            }
            targetNode = targetNode.next;
        }
        return false;
    }

    public int size() {
        return this.size;
    }

    private boolean keyEquality(K key1, K key2) {
        return key1 == key2 || (key1 != null && key1.equals(key2));
    }

    private int getBucketIndex(K key) {
        int hash = getHash(key);
        return Math.floorMod(hash, buckets.length);
    }

    private int getHash(K key) {
        return key != null ? key.hashCode() : 0;
    }

    private void resize() {
        Node<K, V>[] oldBuckets = buckets;
        buckets = new Node[oldBuckets.length * 2];
        size = 0;

        for (Node<K, V> head : oldBuckets) {
            Node<K, V> current = head;
            while (current != null) {
                this.put(current.key, current.value);
                current = current.next;
            }
        }
    }

    public static void main(String[] args) {
        MyHashMap<String, Integer> map = new MyHashMap<>();

        map.put("Alice", 25);
        map.put("Bob", 30);
        map.put("Charlie", 35);

        System.out.println(map.get("Alice"));
        System.out.println(map.get("Bob"));

        System.out.println(map.containsKey("Charlie"));

        map.put("Alice", 26);

        System.out.println(map.get("Alice"));

        map.remove("Bob");

        System.out.println(map.get("Bob"));

        System.out.println(map.size());
    }

}
