package com.nikitasutulov.lab6;

import com.nikitasutulov.lab6.trains.PassengerTrain;
import com.nikitasutulov.lab6.wagons.EconomPassengerWagon;
import com.nikitasutulov.lab6.wagons.LuxPassengerWagon;
import com.nikitasutulov.lab6.wagons.MiddlePassengerWagon;
import com.nikitasutulov.lab6.wagons.PassengerWagon;

import java.util.ArrayList;
import java.util.List;

/**
 * The Main class demonstrates the functionality of a PassengerTrain
 * with different types of PassengerWagons.
 */
public class Main {
    /**
     * The main method creates a PassengerTrain with PassengerWagons,
     * prints the unsorted PassengerWagons, calculates and prints
     * total passengers and luggage, and then prints the PassengerWagons
     * sorted by comfort level.
     *
     * @param args The command-line arguments (not used in this program).
     */
    public static void main(String[] args) {
        List<PassengerWagon> wagons = getTrainWithWagons();
        PassengerTrain train = new PassengerTrain(wagons);
        System.out.println("Passenger Wagons unsorted:");
        printWagons(train.wagons);

        List<Integer> totalPassengersAndLuggage = train.calculateTotalPassengersAndLuggage();
        System.out.println("Total passengers in the train: " + totalPassengersAndLuggage.get(0));
        System.out.println("Total luggage in the train: " + totalPassengersAndLuggage.get(1));

        System.out.println("Passenger Wagons sorted by comfort level:");
        List<PassengerWagon> sortedWagons = train.sortPassengerWagonsByComfortLevel();
        printWagons(sortedWagons);
    }

    /**
     * Prints a list of PassengerWagons.
     *
     * @param wagons The list of PassengerWagons to be printed.
     */
    private static void printWagons(List<PassengerWagon> wagons) {
        for (PassengerWagon wagon : wagons) {
            System.out.println(wagon);
        }
    }

    /**
     * Creates a list of PassengerWagons with different types and
     * configurations.
     *
     * @return The list of PassengerWagons for the train.
     */
    private static List<PassengerWagon> getTrainWithWagons() {
        LuxPassengerWagon luxWagon1 = new LuxPassengerWagon(30, 20);
        MiddlePassengerWagon middleWagon1 = new MiddlePassengerWagon(50, 10);
        LuxPassengerWagon luxWagon2 = new LuxPassengerWagon(15, 5);
        EconomPassengerWagon economWagon1 = new EconomPassengerWagon(55, 5);
        MiddlePassengerWagon middleWagon2 = new MiddlePassengerWagon(35, 15);

        List<PassengerWagon> wagons = new ArrayList<>();
        wagons.add(luxWagon1);
        wagons.add(middleWagon1);
        wagons.add(luxWagon2);
        wagons.add(economWagon1);
        wagons.add(middleWagon2);

        return wagons;
    }
}
