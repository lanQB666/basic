package org.example.Collection.Map;

public interface Map<K,V> {
    
    /**
     * 向map中添加一个键值对
     * @param key 键
     * @param value 值
     * @return 返回旧值，如果没有返回 null
     */
    V put(K key, V value);

    /**
     * 根据键获取值
     * @param key 键
     * @return 返回值
     */
    V get(K key);

    /**
     * 根据键删除键值对
     * @param key 键
     * @return 返回旧值，如果没有返回 null
     */
    V remove(K key);

    /**
     * 判断map中是否包含该键
     * @param key 键
     * @return 返回是否包含
     */
    boolean containsKey(K key);

    class Entry<K,V>{
        K key;
        V value;
        Entry<K,V> next;

        Boolean isHead(){
            return key == null;
        }

        Entry(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }
}
