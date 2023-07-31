package ru.aston.array.custom;

import java.util.Collection;
import java.util.Comparator;

public interface CustomArrayList<E> {

    int size();

    boolean add(E e);

    void add(int index, E element);

    boolean addAll(Collection<? extends E> c);

    void clear();

    E get(int index);

    boolean isEmpty();

    boolean remove(Object o);

    void sort(Comparator<? super E> c);

    void ensureCapacity();

    void ensureCapacity(int newElementsCount);

    int getCapacity();

    void trimToSize();

}
