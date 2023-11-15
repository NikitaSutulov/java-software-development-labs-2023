package com.nikitasutulov.lab5;

import com.nikitasutulov.lab5.entities.*;

import java.io.IOException;
import java.util.*;

/**
 * The Main class contains the main method and utility methods for reading text from the console,
 * processing linguistic expressions, and printing the results.
 */
public class Main {

    /**
     * The main method reads text from the console, extracts and processes linguistic expressions,
     * and prints unfiltered, unique, and sorted unique words.
     *
     * @param args The command-line arguments (not used in this program).
     */
    public static void main(String[] args) {
        try {
            Text text = readTextFromConsole();
            List<Word> words = extractWordsFromText(text);
            List<Word> uniqueWords = filterUniqueWords(words);
            List<Word> sortedUniqueWords = new ArrayList<>(uniqueWords);
            sortedUniqueWords.sort(Comparator.comparing(word -> word.toString().toLowerCase()));

            printLinguisticExpressionArrayWithMessage(words.toArray(new LinguisticExpression[0]), "\nUnfiltered words");
            printLinguisticExpressionArrayWithMessage(uniqueWords.toArray(new LinguisticExpression[0]), "\nUnique words");
            printLinguisticExpressionArrayWithMessage(sortedUniqueWords.toArray(new LinguisticExpression[0]), "\nUnique sorted words");
        } catch (IOException e) {
            throw new RuntimeException("There was a problem while reading the text from the console input: " + e.getMessage());
        }
    }

    /**
     * Reads text from the console, processes it into sentences, and creates a Text object.
     *
     * @return The Text object containing the processed sentences.
     * @throws IOException If there is an error while reading from the console.
     */
    public static Text readTextFromConsole() throws IOException {
        Scanner scanner = new Scanner(System.in);
        String line = scanner.nextLine();
        String[] sentences = line.split("(?<=[.!?])");
        Sentence[] sentenceObjects = new Sentence[sentences.length];

        for (int i = 0; i < sentences.length; i++) {
            Sentence sentence = new Sentence(extractLinguisticExpressions(sentences[i]));
            sentenceObjects[i] = sentence;
        }

        scanner.close();
        return new Text(sentenceObjects);
    }

    /**
     * Extracts linguistic expressions from a given sentence.
     *
     * @param sentence The input sentence.
     * @return An array of LinguisticExpression objects extracted from the sentence.
     */
    public static LinguisticExpression[] extractLinguisticExpressions(String sentence) {
        List<LinguisticExpression> linguisticExpressions = new ArrayList<>();
        List<Letter> currentWordLetters = new ArrayList<>();

        for (char c : sentence.toCharArray()) {
            if (Character.toString(c).matches("[a-zA-Zа-яА-ЯҐґЄєЇїІі'`]|\\d")) {
                currentWordLetters.add(new Letter(c));
            } else {
                if (!currentWordLetters.isEmpty()) {
                    linguisticExpressions.add(new Word(currentWordLetters.toArray(Letter[]::new)));
                    currentWordLetters.clear();
                }
                if (c != ' ') {
                    linguisticExpressions.add(new PunctuationMark(c));
                }
            }
        }

        // Handle the last word if any
        if (!currentWordLetters.isEmpty()) {
            linguisticExpressions.add(new Word(currentWordLetters.toArray(new Letter[0])));
        }

        return linguisticExpressions.toArray(new LinguisticExpression[0]);
    }

    /**
     * Creates a Word object from a given string.
     *
     * @param word The input string representing a word.
     * @return The Word object created from the input string.
     */
    public static Word createWord(String word) {
        Letter[] letters = new Letter[word.length()];

        for (int i = 0; i < word.length(); i++) {
            letters[i] = new Letter(word.charAt(i));
        }

        return new Word(letters);
    }

    /**
     * Extracts words from the given Text object.
     *
     * @param text The input Text object.
     * @return A list of Word objects extracted from the text.
     */
    public static List<Word> extractWordsFromText(Text text) {
        List<Word> words = new ArrayList<>();

        for (Sentence sentence : text.getSentences()) {
            for (LinguisticExpression expression : sentence.getLinguisticExpressions()) {
                if (expression instanceof Word) {
                    words.add((Word) expression);
                }
            }
        }

        return words;
    }

    /**
     * Filters out duplicate words from the given list of words.
     *
     * @param unfilteredWords The list of words with potential duplicates.
     * @return A list of unique words.
     */
    public static List<Word> filterUniqueWords(List<Word> unfilteredWords) {
        Map<String, Word> uniqueWordsMap = new LinkedHashMap<>();

        for (Word word : unfilteredWords) {
            String lowercaseWord = word.toString().toLowerCase();
            uniqueWordsMap.putIfAbsent(lowercaseWord, word);
        }

        return new ArrayList<>(uniqueWordsMap.values());
    }

    /**
     * Prints an array of LinguisticExpressions with a specified message.
     *
     * @param array   The array of LinguisticExpressions to be printed.
     * @param message The message to be printed before the array.
     */
    public static void printLinguisticExpressionArrayWithMessage(LinguisticExpression[] array, String message) {
        System.out.println(message);
        for (LinguisticExpression expression : array) {
            System.out.print(expression.toString() + " ");
        }
        System.out.println();
    }
}
