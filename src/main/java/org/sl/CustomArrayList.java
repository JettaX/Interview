package org.sl;

public class CustomArrayList<T> implements List<T> {
    private static final int DEFAULT_SIZE = 10;
    private T[] arr;
    private int iter;

    public CustomArrayList() {
        arr = (T[]) new Object[DEFAULT_SIZE];
    }

    @Override
    public boolean add(T o) {
        checkCapacity();
        arr[iter] = o;
        iter++;
        return true;
    }

    private void checkCapacity() {
        if (arr.length == iter) {
            T[] copy = (T[]) new Object[arr.length * 2];
            System.arraycopy(arr, 0, copy, 0, iter);
            arr = copy;
        }
    }

    @Override
    public T get(int index) {
        checkBounds(index);
        return arr[index];
    }

    @Override
    public boolean remove(int index) {
        checkBounds(index);

        int numMoved = iter - index - 1;
        if (numMoved > 0) {
            System.arraycopy(arr, index + 1, arr, index, numMoved);
        }
        arr[iter - 1] = null;
        iter--;
        return true;
    }

    @Override
    public boolean contains(T o) {
        for (int i = 0; i < iter; i++) {
            if (arr[i].equals(o)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void clear() {
        arr = (T[]) new Object[arr.length];
    }

    @Override
    public int size() {
        return iter;
    }

    @Override
    public boolean isEmpty() {
        return iter < 1;
    }

    private void checkBounds(int index) {
        if (index >= iter || index < 0) {
            throw new IndexOutOfBoundsException("Index out of bounds for size " + iter + " and index " + index + ".");
        }
    }
}
