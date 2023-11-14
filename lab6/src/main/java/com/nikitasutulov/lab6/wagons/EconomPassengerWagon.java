package com.nikitasutulov.lab6.wagons;

public class EconomPassengerWagon extends PassengerWagon {
    private static final int COMFORT_LEVEL = 1;
    public EconomPassengerWagon(int passengerCount, int luggageCount) {
        super(passengerCount, luggageCount, COMFORT_LEVEL);
    }
}
