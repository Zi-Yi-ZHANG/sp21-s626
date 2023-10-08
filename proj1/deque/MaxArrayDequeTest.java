package deque;

import org.junit.Test;
import static org.junit.Assert.*;

import java.util.Comparator;

public class MaxArrayDequeTest {

    private static class NumComparator<T> implements Comparator<T> {
        public int compare(T item1, T item2) {
            return (int) item1 - (int) item2;
        }
    }

    private static class LenComparator<T> implements Comparator<T> {
        public int compare(T item1, T item2) {
            return item1.toString().length() - item2.toString().length();
        }
    }

    private static class AlphabeticComparator<T> implements Comparator<T> {
        public int compare(T item1, T item2) {
            return item1.toString().length() - item2.toString().length();
        }
    }

    @Test
    public void maxByMultiComparators() {
        MaxArrayDeque<Integer> mad = new MaxArrayDeque<>(new NumComparator());

        for (int i = 0; i < 10; i ++){
            mad.addLast(i);
        }

        assertEquals(9, (int) mad.max());
    }
}
