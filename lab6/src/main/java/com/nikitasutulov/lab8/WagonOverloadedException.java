package com.nikitasutulov.lab8;

/**
 * This exception class is thrown when attempting to load too many people and luggage into a wagon.
 * It extends the base Exception class and provides a default error message.
 */
public class WagonOverloadedException extends Exception {

    /**
     * Default error message indicating that loading too many people and luggage into a wagon is not allowed.
     */
    public static final String MESSAGE = "Too many people and luggage to load to a wagon";

    /**
     * Constructs a new WagonOverloadedException with the default error message.
     */
    public WagonOverloadedException() {
        super(MESSAGE);
    }
}
