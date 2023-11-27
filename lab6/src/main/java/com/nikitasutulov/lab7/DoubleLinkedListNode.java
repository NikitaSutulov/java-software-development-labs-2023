package com.nikitasutulov.lab7;

import com.nikitasutulov.lab6.wagons.PassengerWagon;

/**
 * Represents a node in a doubly linked list that stores objects of type T.
 *
 * @param <T> The type of object stored in the node, extending PassengerWagon.
 */
public class DoubleLinkedListNode<T extends PassengerWagon> {
    /**
     * The value stored in the node.
     */
    public T value;

    /**
     * Reference to the previous node in the doubly linked list.
     */
    public DoubleLinkedListNode<T> prev;

    /**
     * Reference to the next node in the doubly linked list.
     */
    public DoubleLinkedListNode<T> next;

    /**
     * Constructs a new DoubleLinkedListNode with the specified value.
     *
     * @param value The value to be stored in the node.
     */
    public DoubleLinkedListNode(T value) {
        this.value = value;
    }
}
