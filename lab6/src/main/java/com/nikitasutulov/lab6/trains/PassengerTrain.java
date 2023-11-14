package com.nikitasutulov.lab6.trains;

import com.nikitasutulov.lab6.wagons.PassengerWagon;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class PassengerTrain {
    public final List<PassengerWagon> wagons;

    public PassengerTrain(List<PassengerWagon> wagons) {
        this.wagons = wagons;
    }
    public List<Integer> calculateTotalPassengersAndLuggage() {
        int totalPassengers = 0;
        int totalLuggage = 0;

        for (PassengerWagon wagon : wagons) {
            totalPassengers += wagon.getPassengerCount();
            totalLuggage += wagon.getLuggageCount();
        }

        return List.of(totalPassengers, totalLuggage);
    }

    public List<PassengerWagon> sortPassengerWagonsByComfortLevel() {
        List<PassengerWagon> wagonsCopy = new ArrayList<>(wagons);
        wagonsCopy.sort(Comparator.comparingInt(PassengerWagon::getComfortLevel));
        return wagonsCopy;
    }
}
