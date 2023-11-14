package com.nikitasutulov.lab5.entities;

public class Sentence {
    private final LinguisticExpression[] linguisticExpressions;

    public Sentence(LinguisticExpression[] linguisticExpressions) {
        this.linguisticExpressions = linguisticExpressions;
    }

    public LinguisticExpression[] getLinguisticExpressions() {
        return linguisticExpressions;
    }
}
