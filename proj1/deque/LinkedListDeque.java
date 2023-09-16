package deque;

public class LinkedListDeque<T> {

    private Nodes first;
    private Nodes last;
    private int size;

    private Nodes headSentNode;
    private Nodes lastSentNode;
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

        headSentNode = new Nodes();
        lastSentNode = new Nodes();

        headSentNode.next = lastSentNode;
        lastSentNode.prev = headSentNode;

        size = 0;
    }

    public LinkedListDeque(T item) {

        headSentNode = new Nodes();
        lastSentNode = new Nodes();

        first = last = new Nodes(headSentNode, item, lastSentNode);

        size = 1;
    }

    public void addFirst(T item) {

        first = new Nodes(headSentNode, item, headSentNode.next);

        if (last == null) {
            last = first;
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

    public void addLast(T item) {

        last = new Nodes(lastSentNode.prev, item, lastSentNode);

        if (first == null) {
            first = last;
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

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public void printDeque() {

        Nodes p = headSentNode;

        while (p.next != null) {
            p = p.next;
            System.out.print(p.item);
        }

        System.out.println();
    }

    public T removeFirst() {

        if (first == null) return null;

        T result = first.item;

        if (first == last) {
            first = last = null;
            new LinkedListDeque<>();
        } else {
            first = new Nodes(headSentNode, first.next.item, first.next.next);
        }

        size -= 1;
        return result;
    }

    public T removeLast() {

        if (last == null) return null;

        T result = last.item;

        if (first == last) {
            first = last = null;
            new LinkedListDeque<>();
        } else {
            last = new Nodes(last.prev.prev, last.prev.item, lastSentNode);
        }

        size -= 1;
        return result;
    }

    public T get(int index) {

        if (index < 0 || index >= size) return null;

        Nodes p = first;
        for (int i = 0; i < index; i++) {
            p = p.next;
        }
        return p.item;
    }

    public T getRecursive(int index) {

        if (index < 0 || index >= size) return null;

        return getRecursiveHelper(index-1, first.next);
    }

    private T getRecursiveHelper(int count, Nodes currNode) {

        if (count == 0) {
            return currNode.item;
        }

        return getRecursiveHelper(count-1 , currNode.next);
    }
}
