package org.example.Collection.List;

import java.util.Objects;

/**
 * @Author LanQibin
 * @date 2025/05/02 12:23
 **/
public class LinkedList<E> implements List<E>{
    private int size = 0;
    private Node<E> head;
    private Node<E> tail;

    public LinkedList(){
        size = 0;
        head = new Node<>();
        tail = new Node<>(null,head,null);
        head.next = tail;
    }

    protected class Node<E>{
        protected E element;
        protected Node<E> next;
        protected Node<E> prev;

        public Node(){}

        public Node(E element, Node<E> prev, Node<E> next){
            this.element = element;
            this.next = next;
            this.prev = prev;
        }
    }

    @Override
    public boolean add(E element) {
        Node<E> newNode = new Node<>(element,tail.prev,tail);
        tail.prev.next = newNode;
        tail.prev = newNode;
        size++;
        return true;
    }

    @Override
    public void add(int index, E element) {
        if(index < 0 || index > size){
            throw new IndexOutOfBoundsException("Index: "+index+", Size: "+size);
        }
        Node<E> p = head;
        for(int i = 0; i < index; i++){
            p = p.next;
        }
        Node<E> newNode = new Node<>(element,p,p.next);
        p.next.prev = newNode;
        p.next = newNode;
        size++;
    }

    @Override
    public E get(int index) {
        if(index < 0 || index >= size){
            throw new IndexOutOfBoundsException("Index: "+index+", Size: "+size);
        }
        Node<E> p = head.next;
        for(int i = 0; i < index; i++){
            p = p.next;
        }
        return p.element;
    }

    @Override
    public E remove(int index) {
        if(index < 0 || index >= size){
            throw new IndexOutOfBoundsException("Index: "+index+", Size: "+size);
        }
        Node<E> p = head;
        for(int i = 0; i < index; i++){
            p = p.next;
        }
        Node<E> target = p.next;
        p.next = target.next;
        target.next.prev = p;
        size--;
        return target.element;
    }

    @Override
    public boolean remove(Object element) {
        for(Node<E> p = head; p != tail.prev; p = p.next){
            if(Objects.equals(p.next.element,element)){
                Node<E> target = p.next;
                p.next = target.next;
                target.next.prev = p;
                size--;
                return true;
            }
        }
        return false;
    }

    @Override
    public E set(int index, E element) {
        if(index < 0 || index >= size){
            throw new IndexOutOfBoundsException("Index: "+index+", Size: "+size);
        }
        Node<E> p = head.next;
        for(int i = 0; i < index; i++){
            p = p.next;
        }
        E oldElement = p.element;
        p.element = element;
        return oldElement;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public void clear() {
        head.next = tail;
        tail.prev = head;
        size = 0;
    }

    @Override
    public boolean contains(Object element) {
        return indexOf(element) != -1;
    }

    @Override
    public int indexOf(Object element) {
        int index = 0;
        for(Node<E> p = head.next; p != tail; p = p.next,index++){
            if(Objects.equals(p.element,element)){
                return index;
            }
        }
        return -1;
    }
}
