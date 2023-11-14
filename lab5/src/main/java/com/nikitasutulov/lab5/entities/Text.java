package com.nikitasutulov.lab5.entities;

/**
 * The Text class represents a text composed of sentences.
 */
public class Text {
    private final Sentence[] sentences;

    /**
     * Constructs a Text object with an array of sentences.
     *
     * @param linguisticExpressions The array of sentences representing the text.
     */
    public Text(Sentence[] linguisticExpressions) {
        this.sentences = linguisticExpressions;
    }

    /**
     * Gets the array of sentences representing the text.
     *
     * @return The array of sentences representing the text.
     */
    public Sentence[] getSentences() {
        return sentences;
    }
}


