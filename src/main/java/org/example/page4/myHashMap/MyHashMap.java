package org.example.page4.myHashMap;

public class MyHashMap <K,V> {
    MyEntry<? extends K, ? extends V>[] map;
    public MyHashMap(){
        int size = 10; // what size should I start with?
        map = new MyEntry[size];
    }

    public V get(K key){
        int keyHash = key.hashCode(); //investigate the size of this hash
        MyEntry myEntry =  map[keyHash];
        return (V) myEntry.getValue();
    }

    public void put(K key, V value){
        int keyHash = key.hashCode();

        String string = "";
        string.hashCode();
        //what happens with collisions?

        map[keyHash] = new MyEntry<>(key, value);
        //should this return void?
    }
}

