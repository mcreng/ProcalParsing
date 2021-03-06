package org.bychan.core.utils;

import java.util.LinkedList;

/**
 * Created by alext on 2015-02-16.
 */
public class BoundedFifo<T> {

    private final int maxCapacity;
    private final LinkedList<T> unbounded;

    public BoundedFifo(int maxCapacity) {
        this.maxCapacity = maxCapacity;
        this.unbounded = new LinkedList<>();
    }

    public void putLast( final T t) {
        unbounded.add(t);
        while (unbounded.size() > maxCapacity) {
            unbounded.remove();
        }
    }


    public T getFromFront(final int i) {
        T hit = findFromFront(i);
        if (hit == null) {
            throw new IndexOutOfBoundsException(String.valueOf(i));
        }
        return hit;
    }

    public boolean isEmpty() {
        return unbounded.isEmpty();
    }


    public T findFromFront(int i) {
        int expectedIndex = unbounded.size() - 1 - i;
        if (expectedIndex < 0) {
            return null;
        }
        return unbounded.get(expectedIndex);
    }
}
