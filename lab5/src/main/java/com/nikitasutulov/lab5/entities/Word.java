package com.nikitasutulov.lab5.entities;

import java.util.Arrays;

/**
 * The Word class represents a word composed of letters.
 */
public class Word extends LinguisticExpression {
    private final Letter[] letters;

    /**
     * Constructs a Word object with an array of letters.
     *
     * @param letters The array of letters representing the word.
     */
    public Word(Letter[] letters) {
        this.letters = letters;
    }

    /**
     * Gets the array of letters representing the word.
     *
     * @return The array of letters representing the word.
     */
    public Letter[] getLetters() {
        return letters;
    }

    /**
     * Returns a string representation of the word.
     *
     * @return A string representation of the word.
     */
    @Override
    public String toString() {
        return Arrays.toString(
                Arrays.stream(letters).map(Letter::getValue).toArray()
        ).replaceAll("[\\[, \\]]+", "");
    }
}
