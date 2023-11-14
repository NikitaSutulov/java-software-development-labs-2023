package com.nikitasutulov.lab5.entities;

public class Text {
    private final Sentence[] sentences;

    public Text(Sentence[] linguisticExpressions) {
        this.sentences = linguisticExpressions;
    }

    public Sentence[] getSentences() {
        return sentences;
    }
}
