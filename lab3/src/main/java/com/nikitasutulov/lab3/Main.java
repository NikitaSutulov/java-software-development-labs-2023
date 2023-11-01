package com.nikitasutulov.lab3;

import java.io.IOException;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        try {
            String[] words = readWordsFromConsole();
            String[] filteredWords = filterUniqueWords(words);
            String[] sortedUniqueWords = filteredWords.clone();
            Arrays.sort(sortedUniqueWords, Comparator.comparing(String::toLowerCase));
            printStringArrayWithMessage(words, "\nUnfiltered words");
            printStringArrayWithMessage(filteredWords, "\nUnique words");
            printStringArrayWithMessage(sortedUniqueWords, "\nUnique sorted words");
        } catch (IOException e) {
            throw new RuntimeException("There was a problem while reading the text from the console input: " + e.getMessage());
        }
    }

    public static String[] readWordsFromConsole() throws IOException {
        Scanner scanner = new Scanner(System.in);
        String line = scanner.nextLine();
        String[] words = line.split("[,.!?:\\- ]+");
        for (String word : words) {
            if (!word.matches("[a-zA-Zа-яА-Яґєїі'`]+|\\d+")) {
                scanner.close();
                throw new IOException("The words can only contain letters, but there is word which does not: " + word);
            }
        }
        scanner.close();
        return words;
    }

    public static String[] filterUniqueWords(String[] unfilteredWords) {
        Map<String, String> uniqueWordsMap = new LinkedHashMap<>();

        for (String word : unfilteredWords) {
            String lowercaseWord = word.toLowerCase();
            uniqueWordsMap.putIfAbsent(lowercaseWord, word);
        }

        return uniqueWordsMap.values().toArray(new String[0]);
    }

    public static String arrayToString(String[] array) {
        return Arrays.toString(array).replaceAll("[\\[\\]]", "");
    }

    public static void printStringArrayWithMessage(String[] array, String message) {
        System.out.println(message);
        System.out.println(arrayToString(array));
    }

}