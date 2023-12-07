package org.example.page4.myHashMap;

public class MyEntry<K, V> {
    K key;
    V value;

    MyEntry(K key, V value) {
        this.key = key;
        this.value = value;
    }

    public K getKey() {
        return key;
    }

    public V getValue() {
        return value;
    }
}
