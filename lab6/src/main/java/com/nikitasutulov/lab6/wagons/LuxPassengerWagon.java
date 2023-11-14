package com.nikitasutulov.lab6.wagons;

public class LuxPassengerWagon extends PassengerWagon {
    private static final int COMFORT_LEVEL = 3;
    public LuxPassengerWagon(int passengerCount, int luggageCount) {
        super(passengerCount, luggageCount, COMFORT_LEVEL);
    }
}
