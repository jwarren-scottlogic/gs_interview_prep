package org.example.page4.myHashMap;

import org.junit.Test;

import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class MyHashMapTest {
    private String key1 = "key1";
    private String value1 = "value1";
    private String key2 = "key2";
    private String value2 = "value2";
    private String key3 = "key3";
    private String value3 = "value3";
    private String key4 = "key4";
    private String value4 = "value4";
    private String key5 = "key5";
    private String value5 = "value5";
    private String key6 = "key6";
    private String value6 = "value6";
    private String key7 = "key7";
    private String value7 = "value7";
    private String key8 = "key8";
    private String value8 = "value8";
    private String key9 = "key9";
    private String value9 = "value9";
    private String key10 = "key10";
    private String value10 = "value10";
    private String key11 = "key11";
    private String value11 = "value11";
    private String key12 = "key12";
    private String value12 = "value12";
    private String key13 = "key13";
    private String value13 = "value13";

    private MyEntry entry1 = new MyEntry<>(key1, value1);
    private MyEntry entry2 = new MyEntry<>(key2, value2);
    private MyEntry entry3 = new MyEntry<>(key3, value3);
    private MyEntry entry4 = new MyEntry<>(key4, value4);
    private MyEntry entry5 = new MyEntry<>(key5, value5);
    private MyEntry entry6 = new MyEntry<>(key6, value6);
    private MyEntry entry7 = new MyEntry<>(key7, value7);
    private MyEntry entry8 = new MyEntry<>(key8, value8);
    private MyEntry entry9 = new MyEntry<>(key9, value9);
    private MyEntry entry10 = new MyEntry<>(key10, value10);
    private MyEntry entry11 = new MyEntry<>(key11, value11);
    private MyEntry entry12 = new MyEntry<>(key12, value12);
    private MyEntry entry13 = new MyEntry<>(key13, value13);


    private MyHashMap<Object, Object> getOverloadMap(){
        var overloadMap = new MyHashMap<>();
        overloadMap.put(key1, value1);
        overloadMap.put(key2, value2);
        overloadMap.put(key3, value3);
        overloadMap.put(key4, value4);
        overloadMap.put(key5, value5);
        overloadMap.put(key6, value6);
        overloadMap.put(key7, value7);
        overloadMap.put(key8, value8);
        overloadMap.put(key9, value9);
        overloadMap.put(key10, value10);
        overloadMap.put(key11, value11);
        overloadMap.put(key12, value12);
        overloadMap.put(key13, value13);
        return overloadMap;
    }

    @Test
    public void getTest() {
        var hashmap = new MyHashMap<>();
        var entry = new MyEntry(key1, value1);
        hashmap.put(entry);
        assertEquals(hashmap.get(key1), value1);
    }

    @Test
    public void getOverloadTest() {
        var overloadMap = getOverloadMap();
        assertEquals(overloadMap.get(key1), value1);
        assertEquals(overloadMap.get(key2), value2);
        assertEquals(overloadMap.get(key3), value3);
        assertEquals(overloadMap.get(key4), value4);
        assertEquals(overloadMap.get(key5), value5);
        assertEquals(overloadMap.get(key6), value6);
        assertEquals(overloadMap.get(key7), value7);
        assertEquals(overloadMap.get(key8), value8);
        assertEquals(overloadMap.get(key9), value9);
        assertEquals(overloadMap.get(key10), value10);
        assertEquals(overloadMap.get(key11), value11);
        assertEquals(overloadMap.get(key12), value12);
        assertEquals(overloadMap.get(key13), value13);
    }

    @Test
    public void putOverwriteTest() {
        var hashmap = new MyHashMap<>();
        String key = "key1";
        hashmap.put(key, value1);
        hashmap.put(key, value2);
        assertEquals(hashmap.get(key), value2);
    }

    @Test
    public void getEntrySetTest() {
        var overloadMap = getOverloadMap();
        var overloadMapEntrySet = overloadMap.getEntrySet();
        assertEquals(13, overloadMapEntrySet.size());
        assertTrue(setContainsMyEntry(overloadMapEntrySet, entry1));
        assertTrue(setContainsMyEntry(overloadMapEntrySet, entry2));
        assertTrue(setContainsMyEntry(overloadMapEntrySet, entry3));
        assertTrue(setContainsMyEntry(overloadMapEntrySet, entry4));
        assertTrue(setContainsMyEntry(overloadMapEntrySet, entry5));
        assertTrue(setContainsMyEntry(overloadMapEntrySet, entry6));
        assertTrue(setContainsMyEntry(overloadMapEntrySet, entry7));
        assertTrue(setContainsMyEntry(overloadMapEntrySet, entry8));
        assertTrue(setContainsMyEntry(overloadMapEntrySet, entry9));
        assertTrue(setContainsMyEntry(overloadMapEntrySet, entry10));
        assertTrue(setContainsMyEntry(overloadMapEntrySet, entry11));
        assertTrue(setContainsMyEntry(overloadMapEntrySet, entry12));
        assertTrue(setContainsMyEntry(overloadMapEntrySet, entry13));
    }

    private boolean setContainsMyEntry(Set<MyEntry> set, MyEntry myEntry) {
        return set.stream()
                .filter(myEntry::equals)
                .count()==1;
    }
}
