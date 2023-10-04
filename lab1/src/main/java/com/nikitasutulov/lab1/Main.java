package com.nikitasutulov.lab1;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        final int C = 2;
        char a, b, m, n;
        float result = 0;

        System.out.println("Please enter a, b, m, n");

        try {
            char[] values = getValuesFromInput();
            a = values[0];
            b = values[1];
            m = values[2];
            n = values[3];
        } catch (IllegalArgumentException e) {
            throw new RuntimeException("There was a problem with the arguments you entered: " + e.getLocalizedMessage());
        }

        System.out.println("The arguments you entered:\n" +
                "a = " + (int) a +
                ", b = " + (int) b +
                ", m = " + (int) m +
                ", n = " + (int) n);

        try {
            result = calculate(a, b, m, n, C);
            System.out.println("The result of the calculation is " + result);
        } catch (RuntimeException e) {
            throw new RuntimeException("The problem occurred during calculation: " + e.getLocalizedMessage());
        }

    }

    public static char[] getValuesFromInput() throws IllegalArgumentException {
        char[] values = new char[4];
        Scanner scanner = new Scanner(System.in);
        for (int i = 0; i < values.length; i++) {
            if (scanner.hasNextInt()) {
                int intValue = scanner.nextInt();
                if (intValue >= 0) {
                    values[i] = (char) intValue;
                } else {
                    throw new IllegalArgumentException("All arguments must be within the char range (0 <= argument <= 65535).");
                }
            } else if (scanner.hasNext()) {
                String stringValue = scanner.next();
                if (stringValue.length() == 1) {
                    values[i] = stringValue.charAt(0); // char can be also a single symbol
                } else {
                    throw new IllegalArgumentException("All arguments must be of char type.");
                }
            } else {
                throw new IllegalArgumentException("Invalid input. Please provide char values.");
            }
        }
        scanner.close();
        return values;
    }

    public static float calculate(char a, char b, char m, char n, int C) throws IllegalArgumentException, ArithmeticException {
        if (a > n) {
            throw new IllegalArgumentException("a cannot be greater than n");
        }
        if (b > m) {
            throw new IllegalArgumentException("b cannot be greater than m");
        }
        float sum = 0;

        for (char i = a; i <= n; i++) {
            for (char j = b; j <= m; j++) {
                if (i == C) {
                    throw new ArithmeticException("i = C, which leads to division by zero");
                }
                sum += ((float) (i - j) / (float) (i - C));
            }
        }

        return sum;
    }
}
