package org.sl;

public interface List<T> {
    boolean add(T o);

    T get(int index);

    boolean remove(int index);

    boolean contains(T o);

    void clear();

    int size();

    boolean isEmpty();
}
