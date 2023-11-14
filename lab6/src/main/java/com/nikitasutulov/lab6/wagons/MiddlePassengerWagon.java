package com.nikitasutulov.lab6.wagons;

/**
 * The MiddlePassengerWagon class represents a middle-class passenger train wagon
 * with a specified capacity, passenger count, luggage count, and comfort level.
 */
public class MiddlePassengerWagon extends PassengerWagon {
    /**
     * The default comfort level for middle-class passenger wagons.
     */
    private static final int COMFORT_LEVEL = 2;

    /**
     * Constructs a MiddlePassengerWagon object with the specified passenger count
     * and luggage count, using the default comfort level.
     *
     * @param passengerCount The number of passengers to be loaded into the wagon.
     * @param luggageCount   The number of luggage items to be loaded into the wagon.
     */
    public MiddlePassengerWagon(int passengerCount, int luggageCount) {
        super(passengerCount, luggageCount, COMFORT_LEVEL);
    }
}
