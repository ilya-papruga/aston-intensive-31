package ru.aston.array.custom;

import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.Objects;

public class CustomArrayListImpl<E> implements CustomArrayList<E> {

    private static final int DEFAULT_CAPACITY = 10;
    private Object[] elements;
    private int size;

    public CustomArrayListImpl() {
        this.elements = new Object[DEFAULT_CAPACITY];
        this.size = 0;
    }

    public CustomArrayListImpl(int initialCapacity) {
        if (initialCapacity > 0) {
            this.elements = new Object[initialCapacity];
        } else if (initialCapacity < 0) {
            throw new IllegalArgumentException("Wrong Capacity: " + initialCapacity);
        }
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public int getCapacity() {
        return elements.length;

    }

    @Override
    public boolean add(E element) {

        ensureCapacity();

        elements[size++] = element;

        return true;
    }

    @Override
    public void add(int index, E element) {

        ensureCapacity();

        elements[size++] = element;

    }

    @Override
    public boolean addAll(Collection<? extends E> collection) {

        Object[] newElements = collection.toArray();
        int numNew = newElements.length;
        if (numNew == 0)
            return false;

        ensureCapacity(size + newElements.length);
        System.arraycopy(newElements, 0, elements, size, newElements.length);
        size += newElements.length;
        return true;

    }

    @Override
    public void ensureCapacity() {
        if (size == elements.length) {

            elements = Arrays.copyOf(elements, (size * 3) / 2 + 1);
        }
    }

    @Override
    public void ensureCapacity(int newElementsCount) {
        if (newElementsCount > elements.length) {
            int newCapacity = Math.max((elements.length * 3) / 2 + 1, newElementsCount);
            elements = Arrays.copyOf(elements, newCapacity);
        }
    }

    @Override
    public void clear() {

        final Object[] es = elements;
        for (int to = size, i = size = 0; i < to; i++)
            es[i] = null;
    }

    @Override
    public E get(int index) {
        Objects.checkIndex(index, size);
        return (E) elements[index];
    }


    @Override
    public boolean isEmpty() {
        return size == 0;
    }


    public boolean remove(Object o) {
        int index = indexOf(o);
        if (index >= 0) {
            fastRemove(elements, index);
            return true;
        }
        return false;
    }

    private int indexOf(Object o) {
        for (int i = 0; i < size; i++) {
            if (Objects.equals(o, elements[i])) {
                return i;
            }
        }

        return -1;
    }

    private void fastRemove(Object[] es, int i) {
        int newSize = size - 1;
        if (newSize > i) {
            System.arraycopy(es, i + 1, es, i, newSize - i);
        }
        es[size = newSize] = null;
    }


    @Override
    public void sort(Comparator<? super E> c) {

        mergeSort(0, size - 1, c);
    }


    private void mergeSort(int left, int right, Comparator<? super E> comparator) {
        if (left < right) {
            int center = (left + right) / 2;
            mergeSort(left, center, comparator);
            mergeSort(center + 1, right, comparator);
            merge(left, center + 1, right, comparator);
        }
    }

    private void merge(int leftPos, int rightPos, int rightEnd, Comparator<? super E> comparator) {
        Object[] tmpArray = new Object[size];
        int leftEnd = rightPos - 1;
        int tmpPos = leftPos;
        int numElements = rightEnd - leftPos + 1;

        while (leftPos <= leftEnd && rightPos <= rightEnd)
            if (comparator.compare((E) elements[leftPos], (E) elements[rightPos]) <= 0)
                tmpArray[tmpPos++] = elements[leftPos++];
            else
                tmpArray[tmpPos++] = elements[rightPos++];

        while (leftPos <= leftEnd)
            tmpArray[tmpPos++] = elements[leftPos++];

        while (rightPos <= rightEnd)
            tmpArray[tmpPos++] = elements[rightPos++];

        for (int i = 0; i < numElements; i++, rightEnd--)
            elements[rightEnd] = (E) tmpArray[rightEnd];
    }


    public void trimToSize() {
        Object[] emptyData = {};

        if (size < elements.length) {
            elements = (size == 0)
                    ? emptyData
                    : Arrays.copyOf(elements, size);
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("[");
        String delimeter = "";

        for (int i = 0; i < size; i++) {

            sb.append(delimeter).append(elements[i]);
            delimeter = ", ";
        }
        sb.append("]");
        return sb.toString();
    }

}
