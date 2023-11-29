package com.nikitasutulov.lab7;

import com.nikitasutulov.lab6.wagons.PassengerWagon;

import java.util.*;

/**
 * A custom implementation of a set using a doubly linked list, specifically designed to store objects of type T extending PassengerWagon.
 *
 * @param <T> The type of objects stored in the set, extending PassengerWagon.
 */
public class MyWagonSet<T extends PassengerWagon> implements Set<T> {

    private DoubleLinkedListNode<T> head;
    private DoubleLinkedListNode<T> tail;

    /**
     * Constructs an empty MyWagonSet.
     */
    public MyWagonSet() {
        head = null;
        tail = null;
    }

    /**
     * Constructs a MyWagonSet containing a single element.
     *
     * @param wagon The element to be added to the set.
     */
    public MyWagonSet(T wagon) {
        add(wagon);
    }

    /**
     * Constructs a MyWagonSet containing elements from the specified collection.
     *
     * @param collection The collection whose elements are to be added to the set.
     */
    public MyWagonSet(Collection<T> collection) {
        addAll(collection);
    }

    /**
     * Returns the number of elements in this set.
     *
     * @return the number of elements in this set.
     */
    @Override
    public int size() {
        int size = 0;
        DoubleLinkedListNode<T> currentNode = head;
        while (currentNode != null) {
            size++;
            currentNode = currentNode.next;
        }
        return size;
    }

    /**
     * Returns true if this set contains no elements.
     *
     * @return true if this set contains no elements, false otherwise.
     */
    @Override
    public boolean isEmpty() {
        return head == null;
    }

    /**
     * Returns true if this set contains the specified element.
     *
     * @param o The object to be checked for containment in this set.
     * @return true if this set contains the specified element, false otherwise.
     */
    @Override
    public boolean contains(Object o) {
        if (isEmpty()) {
            return false;
        }
        DoubleLinkedListNode<T> currentNode = head;
        while (currentNode != null) {
            if (currentNode.value.equals(o)) {
                return true;
            }
            currentNode = currentNode.next;
        }
        return false;
    }

    /**
     * Returns an iterator over the elements in this set. The elements are returned in the order they were added.
     *
     * @return An iterator over the elements in this set.
     */
    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            private DoubleLinkedListNode<T> current = head;

            /**
             * Returns true if the iteration has more elements.
             *
             * @return true if the iteration has more elements, false otherwise.
             */
            @Override
            public boolean hasNext() {
                return current != null;
            }

            /**
             * Returns the next element in the iteration.
             *
             * @return the next element in the iteration.
             * @throws NoSuchElementException if there are no more elements in the set.
             */
            @Override
            public T next() {
                if (!hasNext()) {
                    throw new NoSuchElementException("No more elements in the set");
                }

                T value = current.value;
                current = current.next;
                return value;
            }

            /**
             * Removes the current element from the set (not supported).
             */
            @Override
            public void remove() {
                MyWagonSet.this.remove(next());
            }
        };
    }

    /**
     * Returns an array containing all of the elements in this set. The order of elements is the order they were added.
     *
     * @return An array containing all the elements in the set.
     */
    @Override
    public Object[] toArray() {
        if (isEmpty()) {
            return new Object[0];
        }
        int size = size();
        Object[] array = new Object[size];
        DoubleLinkedListNode<T> currentNode = head;
        for (int i = 0; i < size; i++) {
            array[i] = currentNode.value;
            currentNode = currentNode.next;
        }
        return array;
    }

    /**
     * Not supported; use {@link #toArray()} instead.
     *
     * @param t1s The array into which the elements of the set are to be stored.
     * @return An array containing all the elements in the set.
     * @throws UnsupportedOperationException if the toArray(T[] a) operation is not supported.
     */
    @Override
    public <T1> T1[] toArray(T1[] t1s) {
        throw new UnsupportedOperationException("toArray(T[] a) operation is not supported; use toArray() instead.");
    }

    /**
     * Adds the specified element to this set if it is not already present.
     *
     * @param t The element to be added to the set.
     * @return true if this set did not already contain the specified element, false otherwise.
     */
    @Override
    public boolean add(T t) {
        if (contains(t)) {
            return false;
        }
        if (isEmpty()) {
            head = new DoubleLinkedListNode<>(t);
            tail = head;
        } else {
            DoubleLinkedListNode<T> newNode = new DoubleLinkedListNode<>(t);
            tail.next = newNode;
            newNode.prev = tail;
            tail = newNode;
        }
        return true;
    }

    /**
     * Removes the specified element from this set if it is present.
     *
     * @param o The object to be removed from the set.
     * @return true if this set contained the specified element, false otherwise.
     */
    @Override
    public boolean remove(Object o) {
        if (isEmpty()) {
            return false;
        }
        if (!contains(o)) {
            return false;
        }
        DoubleLinkedListNode<T> currentNode = head;
        while (currentNode != null) {
            if (currentNode.value.equals(o)) {
                if (size() == 1) {
                    head = null;
                    tail = null;
                } else if (currentNode.equals(tail)) {
                    tail.prev.next = null;
                    tail = tail.prev;
                } else if (currentNode.equals(head)) {
                    head.next.prev = null;
                    head = head.next;
                } else {
                    DoubleLinkedListNode<T> previousNode = currentNode.prev;
                    DoubleLinkedListNode<T> nextNode = currentNode.next;
                    previousNode.next = nextNode;
                    nextNode.prev = previousNode;
                }
                return true;
            }
            currentNode = currentNode.next;
        }
        return false;
    }

    /**
     * Adds all of the elements in the specified collection to this set.
     *
     * @param collection The collection containing elements to be added to this set.
     * @return true if this set changed as a result of the call, false otherwise.
     */
    @Override
    public boolean addAll(Collection<? extends T> collection) {
        boolean modified = false;
        for (T element : collection) {
            if (add(element)) {
                modified = true;
            }
        }
        return modified;
    }

    /**
     * Removes all of the elements from this set that are present in the specified collection.
     *
     * @param collection The collection containing elements to be removed from this set.
     * @return true if this set changed as a result of the call, false otherwise.
     */
    @Override
    public boolean removeAll(Collection<?> collection) {
        boolean modified = false;
        for (Object element : collection) {
            if (remove(element)) {
                modified = true;
            }
        }
        return modified;
    }

    /**
     * Retains only the elements in this set that are present in the specified collection.
     *
     * @param collection The collection containing elements to be retained in this set.
     * @return true if this set changed as a result of the call, false otherwise.
     */
    @Override
    public boolean retainAll(Collection<?> collection) {
        if (isEmpty() || collection.isEmpty()) {
            clear();
            return true;
        }
        MyWagonSet<T> retainedSet = new MyWagonSet<>();
        for (T element : this) {
            if (collection.contains(element)) {
                retainedSet.add(element);
            }
        }
        if (retainedSet.size() <= size()) {
            clear();
            addAll(retainedSet);
            return true;
        }
        return false;
    }

    /**
     * Returns true if this set contains all of the elements of the specified collection.
     *
     * @param collection The collection to be checked for containment in this set.
     * @return true if this set contains all of the elements of the specified collection, false otherwise.
     */
    @Override
    public boolean containsAll(Collection<?> collection) {
        for (Object element : collection) {
            if (!contains(element)) {
                return false;
            }
        }
        return true;
    }

    /**
     * Returns an array containing all of the elements in this set. The order of elements is the order they were added.
     *
     * @param array The array into which the elements of the set are to be stored, if it is big enough;
     *              otherwise, a new array of the same runtime type is allocated for this purpose.
     * @return An array containing all the elements in the set.
     */
    public T[] toArray(T[] array) {
        if (isEmpty()) {
            return Arrays.copyOf(array, 0);
        }

        int size = size();
        if (array.length < size) {
            array = Arrays.copyOf(array, size);
        }

        int index = 0;
        DoubleLinkedListNode<T> currentNode = head;
        while (currentNode != null) {
            array[index++] = currentNode.value;
            currentNode = currentNode.next;
        }

        if (array.length > size) {
            // If the array is larger than needed, set the next element to null
            array[size] = null;
        }

        return array;
    }

    /**
     * Clears all elements from this set.
     */
    @Override
    public void clear() {
        head = null;
        tail = null;
    }
}
