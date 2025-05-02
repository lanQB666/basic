package org.example.Collection.List;

public interface List<E> {
    /**
     * 向列表末尾添加元素
     * @param element 要添加的元素
     * @return 是否添加成功
     */
    boolean add(E element);

    /**
     * 在指定位置插入元素
     * @param index 插入位置
     * @param element 要插入的元素
     */
    void add(int index, E element);

    /**
     * 获取指定位置的元素
     * @param index 元素位置
     * @return 该位置的元素
     */
    E get(int index);

    /**
     * 移除指定位置的元素
     * @param index 要移除元素的位置
     * @return 被移除的元素
     */
    E remove(int index);

    /**
     * 移除指定元素
     * @param element 要移除的元素
     * @return 是否移除成功
     */
    boolean remove(Object element);

    /**
     * 修改指定位置的元素
     * @param index 要修改的位置
     * @param element 新元素
     * @return 原来的元素
     */
    E set(int index, E element);

    /**
     * 获取列表大小
     * @return 列表中的元素个数
     */
    int size();

    /**
     * 判断列表是否为空
     * @return 如果列表为空返回true，否则返回false
     */
    boolean isEmpty();

    /**
     * 清空列表中的所有元素
     */
    void clear();

    /**
     * 判断是否包含指定元素
     * @param element 要查找的元素
     * @return 如果包含返回true，否则返回false
     */
    boolean contains(Object element);

    /**
     * 返回指定元素在列表中第一次出现的位置
     * @param element 要查找的元素
     * @return 元素的索引，如果不存在返回-1
     */
    int indexOf(Object element);
}
