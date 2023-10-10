package com.nikitasutulov.lab2;

public class Main {
    public static void main(String[] args) {
        long[][] A = {
                {2, -6, 183},
                {82, 9, -32},
                {-42, 7, -28},
                {52, 94, 4}
        };
        long[][] B = {
                {83, 92, -43, 2},
                {29, -35, -1, 73},
                {37, 9, 11, 254}
        };
        long[][] C;
        System.out.println("Matrix A:");
        printMatrix(A);
        System.out.println("Matrix B:");
        printMatrix(B);
        try {
            C = multiplyMatrices(A, B);
        } catch (IllegalArgumentException e) {
            throw new RuntimeException("There was an exception caught during the process of multiplying the matrices:\n" +
                    e.getLocalizedMessage());
        }

        System.out.println("Matrix C = A x B:");
        printMatrix(C);

        try {
            double[] averageRowValues = getAverageRowValues(C);
            System.out.println("Average row values of C:");
            printArray(averageRowValues);
        } catch (IllegalArgumentException e) {
            throw new RuntimeException("There was an exception caught while calculating average row values:\n" +
                    e.getLocalizedMessage());
        }
    }

    public static void printArray(double[] array) {
        System.out.print("{ ");
        for (int i = 0; i < array.length; i++) {
            System.out.print(array[i] + " ");
        }
        System.out.println("}");
    }

    public static double[] getAverageRowValues(long[][] matrix) throws IllegalArgumentException {
        if (matrix.length == 0) {
            throw new IllegalArgumentException("The matrix you try to get average row values of is empty.");
        }
        double[] result = new double[matrix.length];
        double rowSum = 0;
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                rowSum += matrix[i][j];
            }
            result[i] = rowSum / matrix[i].length;
        }
        return result;
    }

    public static long[][] multiplyMatrices(long[][] a, long[][] b) throws IllegalArgumentException {
        try {
            checkTwoDimensionalArrayIsRectangular(a);
            checkTwoDimensionalArrayIsRectangular(b);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Invalid matrices:\n" + e.getLocalizedMessage());
        }

        if (a[0].length != b.length) {
            throw new IllegalArgumentException(String.format("Matrices have sizes not comparable with multiplying: a[%d][%d], b[%d][%d]", a.length, a[0].length, b.length, b[0].length));
        }

        long[][] result = new long[a.length][b[0].length];
        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < b[0].length; j++) {
                for (int k = 0; k < a[0].length; k++) {
                    result[i][j] += a[i][k] * b[k][j]; // init values of all the result matrix elements are zero
                }
            }
        }
        return result;
    }

    public static void checkTwoDimensionalArrayIsRectangular(long[][] twoDArray) throws IllegalArgumentException {
        int rowLength = twoDArray[0].length;
        for (int i = 0; i < twoDArray.length; i++) {
            if (twoDArray[i].length != rowLength) {
                System.out.println("Error with 2D array:");
                printMatrix(twoDArray);
                throw new IllegalArgumentException("2D array is not rectangular");
            }
        }
    }

    public static void printMatrix(long[][] matrix) {
        for (int i = 0; i < matrix.length; i++) {
            System.out.print("{ ");
            for (int j = 0; j < matrix[i].length; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println("}");
        }
        System.out.println();
    }
}