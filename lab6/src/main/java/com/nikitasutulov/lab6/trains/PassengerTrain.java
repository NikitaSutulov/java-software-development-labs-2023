package com.nikitasutulov.lab6.trains;

import com.nikitasutulov.lab6.wagons.PassengerWagon;
import com.nikitasutulov.lab7.MyWagonSet;

import java.util.*;

/**
 * The PassengerTrain class represents a train composed of PassengerWagons.
 */
public class PassengerTrain {
    /**
     * The set of PassengerWagons in the train.
     */
    public final Set<PassengerWagon> wagons;

    /**
     * Constructs a PassengerTrain object with the specified set of PassengerWagons.
     *
     * @param wagons The set of PassengerWagons to be included in the train.
     */
    public PassengerTrain(Set<PassengerWagon> wagons) {
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
     * @return A new array of PassengerWagons sorted by comfort level.
     */
    public PassengerWagon[] sortPassengerWagonsByComfortLevel() {
        PassengerWagon[] wagonsArray = wagons.toArray(new PassengerWagon[wagons.size()]);
        Arrays.sort(wagonsArray, Comparator.comparingInt(PassengerWagon::getComfortLevel));
        return wagonsArray;
    }
}
