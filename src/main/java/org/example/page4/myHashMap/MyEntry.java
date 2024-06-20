package org.example.page4.myHashMap;

import java.util.Objects;

public class MyEntry<K, V> {
    K key;
    V value;
    MyEntry<K, V> nextInCollisionList;

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

    public final int getHashCode() {
        return Objects.hashCode(key) ^ Objects.hashCode(value);
    }

    @Override
    public boolean equals(Object entry) {
        if (entry == null) return false;
        if (entry.getClass() == MyEntry.class) {
            return this.key.equals(((MyEntry<?, ?>) entry).key) && this.value.equals(((MyEntry<?, ?>) entry).value);
        }
        return false;
    }
}
