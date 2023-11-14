package com.nikitasutulov.lab5;

import com.nikitasutulov.lab5.entities.*;

import java.io.IOException;
import java.util.*;

public class Main {
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

    public static Text readTextFromConsole() throws IOException {
        Scanner scanner = new Scanner(System.in);
        String line = scanner.nextLine();
        String[] sentences = line.split("[.!?]");
        Sentence[] sentenceObjects = new Sentence[sentences.length];

        for (int i = 0; i < sentences.length; i++) {
            sentenceObjects[i] = new Sentence(extractLinguisticExpressions(sentences[i]));
        }

        scanner.close();
        return new Text(sentenceObjects);
    }

    public static LinguisticExpression[] extractLinguisticExpressions(String sentence) {
        String[] words = sentence.split("[,;:\\- ]+");
        List<LinguisticExpression> linguisticExpressions = new ArrayList<>();

        for (String word : words) {
            if (!word.matches("[a-zA-Zа-яА-ЯҐґЄєЇїІі'`]+|\\d+")) {
                linguisticExpressions.add(new PunctuationMark(word.charAt(0)));
            } else {
                linguisticExpressions.add(createWord(word));
            }
        }

        return linguisticExpressions.toArray(new LinguisticExpression[0]);
    }

    public static Word createWord(String word) {
        Letter[] letters = new Letter[word.length()];

        for (int i = 0; i < word.length(); i++) {
            letters[i] = new Letter(word.charAt(i));
        }

        return new Word(letters);
    }

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

    public static List<Word> filterUniqueWords(List<Word> unfilteredWords) {
        Map<String, Word> uniqueWordsMap = new LinkedHashMap<>();

        for (Word word : unfilteredWords) {
            String lowercaseWord = word.toString().toLowerCase();
            uniqueWordsMap.putIfAbsent(lowercaseWord, word);
        }

        return new ArrayList<>(uniqueWordsMap.values());
    }

    public static void printLinguisticExpressionArrayWithMessage(LinguisticExpression[] array, String message) {
        System.out.println(message);
        for (LinguisticExpression expression : array) {
            System.out.print(expression.toString() + " ");
        }
        System.out.println();
    }
}
