package org.example.page4;

import java.util.HashMap;

public class MyHashMap <K,V> {
    HashMap<K,V> map; // use array
    public MyHashMap(){
        map = new HashMap<>();
    }

    public V get(K key){
        return map.get(key);
    }

    public V put(K key, V value){
        String string = "";
        string.hashCode();
        return map.put(key, value);
    }
}
