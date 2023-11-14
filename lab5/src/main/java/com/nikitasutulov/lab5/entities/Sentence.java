package com.nikitasutulov.lab5.entities;

/**
 * The Sentence class represents a sentence composed of linguistic expressions.
 */
public class Sentence {
    private final LinguisticExpression[] linguisticExpressions;

    /**
     * Constructs a Sentence object with an array of linguistic expressions.
     *
     * @param linguisticExpressions The array of linguistic expressions representing the sentence.
     */
    public Sentence(LinguisticExpression[] linguisticExpressions) {
        this.linguisticExpressions = linguisticExpressions;
    }

    /**
     * Gets the array of linguistic expressions representing the sentence.
     *
     * @return The array of linguistic expressions representing the sentence.
     */
    public LinguisticExpression[] getLinguisticExpressions() {
        return linguisticExpressions;
    }
}
