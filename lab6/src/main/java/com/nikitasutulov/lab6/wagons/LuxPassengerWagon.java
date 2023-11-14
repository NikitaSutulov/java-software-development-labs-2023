package com.nikitasutulov.lab6.wagons;

/**
 * The LuxPassengerWagon class represents a luxury passenger train wagon
 * with a specified capacity, passenger count, luggage count, and comfort level.
 */
public class LuxPassengerWagon extends PassengerWagon {
    /**
     * The default comfort level for luxury passenger wagons.
     */
    private static final int COMFORT_LEVEL = 3;

    /**
     * Constructs a LuxPassengerWagon object with the specified passenger count
     * and luggage count, using the default comfort level.
     *
     * @param passengerCount The number of passengers to be loaded into the wagon.
     * @param luggageCount   The number of luggage items to be loaded into the wagon.
     */
    public LuxPassengerWagon(int passengerCount, int luggageCount) {
        super(passengerCount, luggageCount, COMFORT_LEVEL);
    }
}
