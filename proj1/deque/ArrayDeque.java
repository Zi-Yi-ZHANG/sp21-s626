package deque;

import java.util.Iterator;

public class ArrayDeque<T> implements Deque<T>, Iterable<T>{

    T[] array = (T []) new Object[8];
    private int size;
    private int nextFirst;
    private int nextLast;
    private double useRatio;

    public ArrayDeque () {
        T[] array = (T []) new Object[8];
        this.size = 0;
        this.nextFirst = 3;
        this.nextLast = 4;
        this.useRatio = 0.25;
    }

//    Helper function: move the fromArray elements to the middle of the toArray
    private T[] moveArray(T[] fromArray, T[] toArray) {
        int first = (nextFirst + 1) % array.length;
        int last = (nextLast - 1 + array.length) % array.length;

//        two cases: first == 0 or not
        if (first <= last) {
            System.arraycopy(fromArray, first, toArray, toArray.length / 4, size);
        } else {
            System.arraycopy(fromArray, first, toArray, toArray.length / 4, fromArray.length - first);
            System.arraycopy(fromArray, 0, toArray, toArray.length / 4 + fromArray.length - first, first);
        }

        nextFirst = toArray.length / 4 - 1;
        nextLast = toArray.length / 4 + size;
        return toArray;
    }

//    Helper function: shrink the array when its memory usage is lower than the useRatio
    private T[] shrinkArray(T[] array) {
        T[] newArray = (T[]) new Object[array.length / 2];
        return moveArray(array, newArray);
    }

    @Override
    public void addFirst(T item) {
        if (size >= array.length) {
            int FACTOR = 2;
            T[] newArray = (T []) new Object[array.length * FACTOR];
            array = moveArray(array, newArray);
//            System.arraycopy(array, 0, newArray, 0, array.length);
        }

        array[nextFirst] = item;
        nextFirst = (nextFirst - 1 + array.length) % array.length;
        size += 1;
    }

    @Override
    public void addLast(T item) {
        if (size >= array.length) {
            int FACTOR = 2;
            T[] newArray = (T []) new Object[array.length * FACTOR];
            array = moveArray(array, newArray);
//            System.arraycopy(array, 0, newArray, 0, array.length);
        }

        array[nextLast] = item;
        nextLast = (nextLast + 1) % array.length;
        size += 1;
    }

//    @Override
//    public boolean isEmpty() {
//        return size == 0;
//    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void printDeque() {
        for (T item : array) {
            System.out.print(item + " ");
        }

        System.out.println();
    }

    @Override
    public T removeFirst() {

        if (isEmpty()) return null;

//        for (int i = 0; i <= size-2; i++) {
//            array[i] = array[i + 1];
//        }

        int first = (nextFirst + 1) % array.length;
        T firstItem = array[first];
        array[first] = null;
        nextFirst = first;
        size -= 1;

        if (size != 0 && (double) size / array.length < useRatio) array = shrinkArray(array);

        return firstItem;
    }

    @Override
    public T removeLast() {
        if (isEmpty()) return null;

        if (size != 1 && (double) size / array.length <= useRatio) array = shrinkArray(array);

        int last = (nextLast - 1 + array.length) % array.length;
        T lastItem = array[last];
        array[last] = null;
        nextLast = last;
        size -= 1;

        return lastItem;
    }

    @Override
    public T get(int index) {
        if (index < 0 || index >= size) return null;

        int position = (nextFirst + 1 + index) % array.length;
        return array[position];
    }

    private class ArrayListIterator implements Iterator<T> {
        private int pos;

        public ArrayListIterator() {
            pos = 0;
        }

        public boolean hasNext() {
            return pos < size;
        }

        public T next() {
            T item = get(pos);
            pos += 1;
            return item;
        }
    }

    @Override
    public Iterator<T> iterator() {
        return new ArrayListIterator();
    }

    @Override
    public boolean equals(Object other) {
        ArrayDeque<T> o = (ArrayDeque<T>) other;

        if (this == o) return true;

        if (o.size == size && o instanceof ArrayDeque) {
            for (int i = 0; i < size; i++) {
                if (get(i) != o.get(i)) return false;
            }
        }

        return true;
    }
}
