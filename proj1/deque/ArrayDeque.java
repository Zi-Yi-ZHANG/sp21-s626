package deque;

public class ArrayDeque<T> {

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

//    Helper function
    private T[] moveArray(T[] fromArray, T[] toArray) {
        int first = (nextFirst + 1) % array.length;
        int last = (nextLast - 1 + array.length) % array.length;

//        two cases: first == 0 or not
        if (first < last) {
            System.arraycopy(fromArray, first, toArray, toArray.length / 4, size);
        } else {
            System.arraycopy(fromArray, first, toArray, toArray.length / 4, fromArray.length - first);
            System.arraycopy(fromArray, 0, toArray, toArray.length / 4 + fromArray.length - first, first);
        }

        nextFirst = toArray.length / 4 - 1;
        nextLast = toArray.length / 4 + size;
        return toArray;
    }

//    Helper function
    private T[] shrinkArray(T[] array) {
        T[] newArray = (T[]) new Object[array.length / 2];
        return moveArray(array, newArray);
    }

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

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public void printDeque() {
        for (T item : array) {
            System.out.print(item + " ");
        }

        System.out.println();
    }

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

    public T get(int index) {
        if (index < 0 || index >= size) return null;

        int position = (nextFirst + 1 + index) % array.length;
        return array[position];
    }
}
