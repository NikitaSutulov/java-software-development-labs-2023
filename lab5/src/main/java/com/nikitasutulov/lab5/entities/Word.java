package com.nikitasutulov.lab5.entities;

import java.util.Arrays;

public class Word extends LinguisticExpression {
    private final Letter[] letters;

    public Word(Letter[] letters) {
        this.letters = letters;
    }

    public Letter[] getLetters() {
        return letters;
    }

    @Override
    public String toString() {
        return Arrays.toString(
                Arrays.stream(letters).map(Letter::getValue).toArray()
        ).replaceAll("[\\[, \\]]+", "");
    }
}
