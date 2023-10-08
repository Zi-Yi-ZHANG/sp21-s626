package deque;

import java.util.Iterator;

public class LinkedListDeque<T> implements Deque<T>, Iterable<T> {

    private Nodes firstNode;
    private Nodes lastNode;
    private int size;

    private Nodes headSentinel;
    private Nodes lastSentinel;
//
//    public T prevNodeItem;
//    public T lastNodeItem;

    private class Nodes {
        T item;
        Nodes prev;
        Nodes next;

        public Nodes() {
            item = null;
            prev = next = null;
        }

        public Nodes(Nodes prevNodes, T item, Nodes nextNodes) {

            // itemNode setting
            this.item = item;
            this.prev = prevNodes;
            this.next = nextNodes;

            // prevNodes setting
            prevNodes.next = this;

            // nextNodes setting
            nextNodes.prev = this;
        }
    }

//    public void setSents(Nodes headSentNode, Nodes lastSentNode) {
//        this.headSent = headSentNode;
//        this.lastSent = lastSentNode;
//    }

    public LinkedListDeque() {

        headSentinel = new Nodes();
        lastSentinel = new Nodes();

        headSentinel.next = lastSentinel;
        lastSentinel.prev = headSentinel;

        size = 0;
    }

    public LinkedListDeque(T item) {

        headSentinel = new Nodes();
        lastSentinel = new Nodes();

        firstNode = lastNode = new Nodes(headSentinel, item, lastSentinel);

        size = 1;
    }

    @Override
    public void addFirst(T item) {

        firstNode = new Nodes(headSentinel, item, headSentinel.next);

        if (lastNode == null) {
            lastNode = firstNode;
        }

//        if (first == null) {
//            first = new Nodes(null, item, null);
//        } else {
//            Nodes temp = new Nodes(null, item, first);
//
////        first.prev = temp;
////        first = temp;
//            first = new Nodes(temp, first.item, first.next);
//        }

        size += 1;
    }

    @Override
    public void addLast(T item) {

        lastNode = new Nodes(lastSentinel.prev, item, lastSentinel);

        if (firstNode == null) {
            firstNode = lastNode;
        }

//        if (last == null) {
//            last = new Nodes(null, item, null);
//        } else {
//            Nodes temp = new Nodes(last, item, null);
//
//            last.next = temp;
//            last = temp;
////        last = new Nodes(last.prev, last.item, null);
//        }

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

        Nodes p = headSentinel;

        while (p.next != null) {
            p = p.next;
            System.out.print(p.item);
        }

        System.out.println();
    }

    @Override
    public T removeFirst() {

        if (firstNode == null) return null;

        T result = firstNode.item;

        if (firstNode == lastNode) {
            firstNode = lastNode = null;
            new LinkedListDeque<>();
        } else {
            firstNode = new Nodes(headSentinel, firstNode.next.item, firstNode.next.next);
        }

        size -= 1;
        return result;
    }

    @Override
    public T removeLast() {

        if (lastNode == null) return null;

        T result = lastNode.item;

        if (firstNode == lastNode) {
            firstNode = lastNode = null;
            new LinkedListDeque<>();
        } else {
            lastNode = new Nodes(lastNode.prev.prev, lastNode.prev.item, lastSentinel);
        }

        size -= 1;
        return result;
    }

    @Override
    public T get(int index) {

        if (index < 0 || index >= size) return null;

        Nodes p = firstNode;
        for (int i = 0; i < index; i++) {
            p = p.next;
        }
        return p.item;
    }

    public T getRecursive(int index) {
        if (index < 0 || index >= size) return null;
        return getRecursiveHelper(index-1, firstNode.next);
    }

    private T getRecursiveHelper(int count, Nodes currNode) {

        if (count == 0) {
            return currNode.item;
        }

        return getRecursiveHelper(count-1 , currNode.next);
    }

    private class LinkedListIterator implements Iterator<T> {
        private int pos;

        public LinkedListIterator() {
            pos = 0;
        }

        public boolean hasNext() {
            return pos < size;
        }

        public T next() {
            Nodes p = headSentinel.next;

            for (int step = 0; step < pos; step += 1) {
                p = p.next;
            }

            pos += 1;
            return p.item;
        }
    }

    @Override
    public Iterator<T> iterator() {
        return new LinkedListIterator();
    }

    @Override
    public boolean equals(Object other) {
        LinkedListDeque<T> o = (LinkedListDeque) other;

        if (this == o) return true;

        if (o.size == size && o instanceof LinkedListDeque) {

            Nodes p = firstNode;
            Nodes op = o.firstNode;

            while(p.next != null) {

                if (op.item != p.item) return false;

                op = op.next;
                p = p.next;
            }
        }

        return true;
    }
}
