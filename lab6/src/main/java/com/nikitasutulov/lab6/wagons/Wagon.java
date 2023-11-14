package com.nikitasutulov.lab6.wagons;

/**
 * The abstract Wagon class represents a generic train wagon with a specified capacity.
 */
public abstract class Wagon {
    /**
     * The capacity of the wagon.
     */
    private final int capacity;

    /**
     * Constructs a Wagon object with the specified capacity.
     *
     * @param capacity The capacity of the wagon.
     */
    public Wagon(int capacity) {
        this.capacity = capacity;
    }
}
