package org.example.Collection.List;

import java.util.Objects;

/**
 * @Author LanQibin
 * @date 2025/05/02 12:08
 **/
public class ArrayList<E> implements List<E> {

    private static final int INIT_SIZE = 10;
    private Object[] table;

    private int size = 0;

    public ArrayList(){
        this.table = new Object[INIT_SIZE];
    }

    public ArrayList(int size){
        this.table = new Object[size];
    }

    @Override
    public boolean add(E element) {
        if(size==table.length){
            resize();
        }
        table[size++]=element;
        return true;
    }

    private void resize(){
        Object[] newTable=new Object[table.length*2];
        System.arraycopy(table,0,newTable,0,table.length);
        table=newTable;
    }

    @Override
    public void add(int index, E element) {
        if(index<0||index>size){
            throw new IndexOutOfBoundsException("Index: "+index+", Size: "+size);
        }
        if(size==table.length){
            resize();
        }
        System.arraycopy(table,index,table,index+1,size-index);
        table[index]=element;
        size++;
    }

    @Override
    public E get(int index) {
        if(index<0||index>=size){
            throw new IndexOutOfBoundsException("Index: "+index+", Size: "+size);
        }
        return (E)table[index];
    }

    @Override
    public E remove(int index) {
        if(index<0||index>=size){
            throw new IndexOutOfBoundsException("Index: "+index+", Size: "+size);
        }
        E oldValue=(E)table[index];
        System.arraycopy(table,index+1,table,index,size-index-1);
        table[--size]=null;
        return (E)oldValue;
    }

    @Override
    public boolean remove(Object element) {
        for(int i=0;i<size;i++){
            if(Objects.equals(table[i],element)){
                remove(i);
                return true;
            }
        }
        return false;
    }

    @Override
    public E set(int index, E element) {
        if(index<0||index>=size){
            throw new IndexOutOfBoundsException("Index: "+index+", Size: "+size);
        }
        E oldValue=(E)table[index];
        table[index] = element;
        return oldValue;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size==0;
    }

    @Override
    public void clear() {
        // 清除所有元素的引用，防止内存泄漏
        for (int i = 0; i < size; i++) {
            table[i] = null;
        }
        size = 0;
    }

    @Override
    public boolean contains(Object element) {
        return indexOf(element)!=-1;
    }

    @Override
    public int indexOf(Object element) {
        for(int i=0;i<size;i++){
            if(Objects.equals(table[i],element)){
                return i;
            }
        }
        return -1;
    }
}
