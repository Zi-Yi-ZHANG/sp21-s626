package deque;

import java.util.Comparator;

public class MaxArrayDeque<T> extends ArrayDeque<T> {

    Comparator<T> defaultComp;

    public MaxArrayDeque(Comparator<T> c) {
        this.defaultComp = c;
    }

    public T max() {
        if (size() == 0) return null;

        T maxItem = get(0);
        for (int i = 1; i < size(); i++) {
            if (defaultComp.compare(maxItem, get(i)) < 0) maxItem = get(i);
        }
        return maxItem;
    }

    public T max(Comparator<T> c) {
        if (size() == 0) return null;

        T maxItem = get(0);
        for (int i = 1; i < size(); i++) {
            if (c.compare(maxItem, get(i)) < 0) maxItem = get(i);
        }
        return maxItem;
    }
}
