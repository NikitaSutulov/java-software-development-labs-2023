package com.nikitasutulov.lab6.trains;

import com.nikitasutulov.lab6.wagons.PassengerWagon;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * The PassengerTrain class represents a train composed of PassengerWagons.
 */
public class PassengerTrain {
    /**
     * The list of PassengerWagons in the train.
     */
    public final List<PassengerWagon> wagons;

    /**
     * Constructs a PassengerTrain object with the specified list of PassengerWagons.
     *
     * @param wagons The list of PassengerWagons to be included in the train.
     */
    public PassengerTrain(List<PassengerWagon> wagons) {
        this.wagons = wagons;
    }

    /**
     * Calculates the total number of passengers and luggage in the train.
     *
     * @return A list containing two elements: total passengers and total luggage.
     */
    public List<Integer> calculateTotalPassengersAndLuggage() {
        int totalPassengers = 0;
        int totalLuggage = 0;

        for (PassengerWagon wagon : wagons) {
            totalPassengers += wagon.getPassengerCount();
            totalLuggage += wagon.getLuggageCount();
        }

        return List.of(totalPassengers, totalLuggage);
    }

    /**
     * Sorts the PassengerWagons in the train by comfort level.
     *
     * @return A new list of PassengerWagons sorted by comfort level.
     */
    public List<PassengerWagon> sortPassengerWagonsByComfortLevel() {
        List<PassengerWagon> wagonsCopy = new ArrayList<>(wagons);
        wagonsCopy.sort(Comparator.comparingInt(PassengerWagon::getComfortLevel));
        return wagonsCopy;
    }
}
