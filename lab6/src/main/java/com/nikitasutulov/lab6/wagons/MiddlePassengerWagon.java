package com.nikitasutulov.lab6.wagons;

public class MiddlePassengerWagon extends PassengerWagon{
    private static final int COMFORT_LEVEL = 2;
    public MiddlePassengerWagon(int passengerCount, int luggageCount) {
        super(passengerCount, luggageCount, COMFORT_LEVEL);
    }
}
