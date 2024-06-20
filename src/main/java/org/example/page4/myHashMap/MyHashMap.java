package org.example.page4.myHashMap;

import java.util.HashSet;
import java.util.Set;

public class MyHashMap<K, V> {
    MyEntry<? extends K, ? extends V>[] map;
    int mapSize;

    public MyHashMap() {
        mapSize = 10; // what size should I start with?
        map = new MyEntry[mapSize];
    }

    public V get(K key) {
        int index = getIndex(key); //investigate the size of this hash
        MyEntry linkedListEntry = map[index];
        while (true) {
            if (linkedListEntry.getKey() == key) return (V) linkedListEntry.getValue();

            if (linkedListEntry.nextInCollisionList == null) {
                throw new RuntimeException("No entry that matches key");
            }
            linkedListEntry = linkedListEntry.nextInCollisionList;
        }
    }

    public V put(K key, V value) {
        MyEntry<K, V> entry = new MyEntry<>(key, value);
        return put(entry);
    }

    public V put(MyEntry<K, V> entry) {
        K key = entry.key;
        V value = entry.value;
        int index = getIndex(key);


        if (map[index] == null) {
            map[index] = entry;
            return value;
        }
        if (map[index].key == key) {
            if (map[index].value == value) return value;
            map[index] = entry;
            return value;
        }

        MyEntry<K, V> linkedListEntry = (MyEntry<K, V>) map[index];
        while (true) {
            if (linkedListEntry.nextInCollisionList == null) {
                linkedListEntry.nextInCollisionList = entry;
                return value;
            }
            linkedListEntry = linkedListEntry.nextInCollisionList;
        }
    }

    public Set<MyEntry> getEntrySet() {
        Set<MyEntry> set = new HashSet<>();
        for (int i = 0; i < mapSize; i++) {
            MyEntry entry = map[i];
            if (entry == null) continue;
            set.add(entry);
            while (entry.nextInCollisionList != null) {
                entry = entry.nextInCollisionList;
                set.add(entry);
            }
        }
        return set;
    }

    private int getIndex(K key) {
        int keyHash = key.hashCode();
        return keyHash % mapSize;
    }

}

