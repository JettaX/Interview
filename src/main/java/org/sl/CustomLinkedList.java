package org.sl;

public class CustomLinkedList<T> implements List<T> {
    private Node<T> head;
    private Node<T> tail;
    private int size;

    private static class Node<T> {
        private T value;
        private Node<T> next;
        private Node<T> previous;

        public Node(T value) {
            this.value = value;
        }
    }

    @Override
    public boolean add(T o) {
        Node<T> node = new Node<>(o);
        if (head == null) {
            head = node;
            tail = node;
        } else {
            tail.next = node;
            node.previous = tail;
            tail = node;
        }
        size++;
        return true;
    }

    @Override
    public T get(int index) {
        return findNode(index).value;
    }

    @Override
    public boolean remove(int index) {
        Node<T> node = findNode(index);
        if (size == 1) {
            head = null;
            tail = null;
        } else if (node.next != null && node.previous != null) {
            node.next.previous = node.previous;
            node.previous.next = node.next;
        } else if (node.next == null) {
            tail = node.previous;
            tail.next = null;
        } else {
            head = head.next;
            head.previous = null;
        }
        size--;
        return true;
    }

    @Override
    public boolean contains(T o) {
        return findNode(o) != null;
    }

    @Override
    public void clear() {
        head = null;
        tail = null;
        size = 0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size < 1;
    }

    private Node<T> findNode(int index) {
        checkBounds(index);
        Node<T> current = head;
        for (int i = 0; i < index; i++) {
            current = current.next;
        }
        return current;
    }

    private Node<T> findNode(T object) {
        Node<T> current = head;
        for (int i = 0; i < size; i++) {
            if (current.value.equals(object)) {
                return current;
            }
            current = current.next;
        }
        return null;
    }

    private void checkBounds(int index) {
        if (index >= size || index < 0) {
            throw new IndexOutOfBoundsException("Index out of bounds for size " + size + " and index " + index + ".");
        }
    }
}
