package com.nikitasutulov.lab5.entities;

public class Letter {
    private final char value;
    public Letter(char value) {
        if (!Character.toString(value).matches("[a-zA-Zа-яА-ЯҐґЄєЇїІі]")) {
            throw new IllegalArgumentException("Tried to pass a non-letter char as an argument of Letter constructor.");
        }
        this.value = value;
    }

    public char getValue() {
        return value;
    }
}
