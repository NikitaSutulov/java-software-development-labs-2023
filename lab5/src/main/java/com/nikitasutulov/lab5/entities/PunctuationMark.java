package com.nikitasutulov.lab5.entities;

/**
 * The PunctuationMark class represents a punctuation mark.
 */
public class PunctuationMark extends LinguisticExpression {
    private final char value;

    /**
     * Constructs a PunctuationMark object with the specified character value.
     *
     * @param value The character value representing the punctuation mark.
     * @throws IllegalArgumentException If the provided character is not a valid punctuation mark.
     */
    public PunctuationMark(char value) {
        if (!Character.toString(value).matches("[.,:\\-;?!]")) {
            throw new IllegalArgumentException("Tried to pass a non-punctuation mark char '" + value + "' as an argument of PunctuationMark constructor.");
        }
        this.value = value;
    }

    /**
     * Gets the character value of the punctuation mark.
     *
     * @return The character value of the punctuation mark.
     */
    public char getValue() {
        return value;
    }

    /**
     * Returns a string representation of the punctuation mark.
     *
     * @return A string representation of the punctuation mark.
     */
    @Override
    public String toString() {
        return Character.toString(value);
    }
}
