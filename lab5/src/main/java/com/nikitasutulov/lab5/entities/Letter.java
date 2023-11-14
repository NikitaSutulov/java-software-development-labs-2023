package com.nikitasutulov.lab5.entities;

/**
 * The Letter class represents a single letter in the alphabet.
 */
public class Letter {
    private final char value;

    /**
     * Constructs a Letter object with the specified character value.
     *
     * @param value The character value representing the letter.
     * @throws IllegalArgumentException If the provided character is not a letter.
     */
    public Letter(char value) {
        if (!Character.toString(value).matches("[a-zA-Zа-яА-ЯҐґЄєЇїІі]")) {
            throw new IllegalArgumentException("Tried to pass a non-letter char as an argument of Letter constructor.");
        }
        this.value = value;
    }

    /**
     * Gets the character value of the letter.
     *
     * @return The character value of the letter.
     */
    public char getValue() {
        return value;
    }
}
