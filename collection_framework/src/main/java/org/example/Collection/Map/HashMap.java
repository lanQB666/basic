package org.example.Collection.Map;

import org.example.Collection.List.ArrayList;

import java.util.Objects;

/**
 * @Author LanQibin
 * @date 2025/05/02 14:48
 **/
public class HashMap<K, V> implements Map<K, V> {

    private static final int INIT_SIZE = 16;
    private ArrayList<Entry<K, V>> table;
    private double loadFactor = 0.75;
    private int size = 0;

    public HashMap() {
        table = new ArrayList<>(INIT_SIZE);
        init(table);
    }

    private void init(ArrayList<Entry<K, V>> table) {
        for (int i = 0; i < INIT_SIZE; i++) {
            table.set(i, new Entry<>(null, null));
        }
    }

    private int hash(K key){
        int h;
        return key == null ? 0 : (h = key.hashCode()) ^ (h >>> 16);
    }

    private Entry<K,V> getEntryPrev(int index, K key){
        Entry<K, V> entry = table.get(index);
        while(entry.next != null){
            if(Objects.equals(entry.next.key, key)){
                break;
            }
            entry = entry.next;
        }
        return entry;
    }

    private Entry<K,V> getEntryEnd(int index){
        Entry<K, V> entry = table.get(index);
        while (entry.next != null){
            entry = entry.next;
        }
        return entry;
    }

    @Override
    public V put(K key, V value) {
        int hash = hash(key);
        int index = hash % table.size();
        Entry<K, V> entry = getEntryPrev(index, key);

        if(!entry.isHead()){
            // 1. entry 存在
            entry.value = value;
        }else{
            // 2. entry 不存在
            entry.next = new Entry<>(key,value);
            size++;
        }
        if(isNeedResize()){
            resize();
        }
        return null;
    }

    @Override
    public V get(K key) {
        int hash = hash(key);
        int index = hash % table.size();
        Entry<K, V> entry = getEntryPrev(index, key);
        if(entry.isHead()){
            return null;
        }
        return entry.value;
    }

    @Override
    public V remove(K key) {
        int hash = hash(key);
        int index = hash % table.size();
        Entry<K, V> entry = getEntryPrev(index, key);
        if(entry.isHead()){
            return null;
        }
        Entry<K, V> oldEntry = entry.next;
        entry.next = oldEntry.next;
        size--;
        return oldEntry.value;
    }

    @Override
    public boolean containsKey(K key) {
        int hash = hash(key);
        int index = hash % table.size();
        Entry<K, V> entry = getEntryPrev(index, key);
        return !entry.isHead();
    }

    private Boolean isNeedResize(){
        return size > table.size()*loadFactor;
    }

    private void resize(){

        ArrayList<Entry<K,V>> newTable = new ArrayList<>(table.size() * 2);
        init(newTable);

        for (int i = 0; i < table.size(); i++) {
            Entry<K, V> entry = table.get(i);
            while (entry.next != null) {
                int hash = hash(entry.next.key);
                if(isNeedMove(hash)){
                    // 1. 迁移到另一半
                    Entry<K, V> entryEnd = getEntryEnd(i + table.size());
                    entryEnd.next = entry.next;
                    entry.next = entry.next.next;
                }else{
                    // 2. 保留
                    entry = entry.next;
                }
            }
        }

        table = newTable;
    }

    private Boolean isNeedMove(int hash){
        return (hash^table.size()) == 0;
    }

}
