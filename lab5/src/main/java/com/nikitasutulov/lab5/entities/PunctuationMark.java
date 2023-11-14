package com.nikitasutulov.lab5.entities;

public class PunctuationMark extends LinguisticExpression {
    private final char value;

    public PunctuationMark(char value) {
        if (!Character.toString(value).matches("[.,:\\-;?!]")) {
            throw new IllegalArgumentException("Tried to pass a non-punctuation mark char as an argument of PunctuationMark constructor.");
        }
        this.value = value;
    }

    public char getValue() {
        return value;
    }

    @Override
    public String toString() {
        return Character.toString(value);
    }
}
