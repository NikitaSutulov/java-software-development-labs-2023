package com.nikitasutulov.lab6.wagons;

/**
 * The EconomPassengerWagon class represents an economical passenger train wagon
 * with a specified capacity, passenger count, luggage count, and comfort level.
 */
public class EconomPassengerWagon extends PassengerWagon {
    /**
     * The default comfort level for economical passenger wagons.
     */
    private static final int COMFORT_LEVEL = 1;

    /**
     * Constructs an EconomPassengerWagon object with the specified passenger count
     * and luggage count, using the default comfort level.
     *
     * @param passengerCount The number of passengers to be loaded into the wagon.
     * @param luggageCount   The number of luggage items to be loaded into the wagon.
     */
    public EconomPassengerWagon(int passengerCount, int luggageCount) {
        super(passengerCount, luggageCount, COMFORT_LEVEL);
    }
}
