package com.nikitasutulov.lab4;

import java.util.Arrays;
import java.util.Comparator;

public class Main {
    /**
     * The main method of the program. It creates an array of "Furniture" objects, sorts them by name (ascending) and cost (descending), and prints the sorting results.
     *
     * @param args Command-line arguments
     */
    public static void main(String[] args) {
        Furniture[] furnitureArray = new Furniture[]{
                new Furniture("Sofa", 220, 38, "Fabric", 165),
                new Furniture("Table", 150, 25, "Wood", 100),
                new Furniture("Bed", 200, 35, "Metal", 160),
                new Furniture("Chair", 50, 10, "Wood", 40),
                new Furniture("Lamp", 20, 5, "Metal", 40),
                new Furniture("Desk", 90, 18, "Wood", 110),
                new Furniture("Bench", 45, 9, "Metal", 92),
                new Furniture("TV Stand", 75, 20, "Wood", 100),
                new Furniture("Chair", 60, 12, "Wood", 45),
                new Furniture("Table", 100, 20, "Wood", 120),
                new Furniture("Sofa", 200, 35, "Fabric", 160),
                new Furniture("Lamp", 25, 6, "Metal", 45),
                new Furniture("Bed", 300, 40, "Metal", 180),
                new Furniture("Bench", 40, 8, "Metal", 90),
                new Furniture("TV Stand", 80, 22, "Wood", 105),
                new Furniture("Desk", 80, 15, "Wood", 100),
        };

        System.out.println("Furniture before sorting:");
        for (Furniture piece : furnitureArray) {
            System.out.println(piece);
        }

        Arrays.sort(furnitureArray, Comparator
                .comparing(Furniture::getName)
                .thenComparing(Furniture::getCost, Comparator.reverseOrder()));

        System.out.println("\nFurniture after sorting by name (ascending) and cost (descending):");
        for (Furniture piece : furnitureArray) {
            System.out.println(piece);
        }
    }

}