package com.nikitasutulov.lab6.wagons;

import com.nikitasutulov.lab8.WagonOverloadedException;

/**
 * The PassengerWagon class represents a passenger train wagon with a specified capacity,
 * passenger count, luggage count, and comfort level.
 */
public class PassengerWagon extends Wagon {
    /**
     * The default capacity for all passenger wagons.
     */
    private static final int CAPACITY = 60;

    /**
     * The number of passengers currently in the wagon.
     */
    protected int passengerCount;

    /**
     * The number of luggage items currently in the wagon.
     */
    protected int luggageCount;

    /**
     * The comfort level of the passenger wagon.
     */
    protected final int comfortLevel;

    /**
     * Constructs a PassengerWagon object with the specified passenger count,
     * luggage count, and comfort level.
     *
     * @param passengerCount The number of passengers to be loaded into the wagon.
     * @param luggageCount   The number of luggage items to be loaded into the wagon.
     * @param comfortLevel   The comfort level of the passenger wagon.
     * @throws WagonOverloadedException If the sum of passengerCount and luggageCount exceeds the capacity.
     */
    public PassengerWagon(int passengerCount, int luggageCount, int comfortLevel) throws WagonOverloadedException {
        super(CAPACITY);

        if (passengerCount < 0) {
            throw new IllegalArgumentException("Passenger count cannot be less than zero");
        }
        if (luggageCount < 0) {
            throw new IllegalArgumentException("Luggage count cannot be less than zero");
        }
        if (comfortLevel < 0) {
            throw new IllegalArgumentException("Comfort level cannot be less than zero");
        }

        if (passengerCount + luggageCount > CAPACITY) {
            throw new WagonOverloadedException();
        }

        this.passengerCount = passengerCount;
        this.luggageCount = luggageCount;
        this.comfortLevel = comfortLevel;
    }

    /**
     * Gets the number of passengers currently in the wagon.
     *
     * @return The number of passengers in the wagon.
     */
    public int getPassengerCount() {
        return passengerCount;
    }

    /**
     * Gets the number of luggage items currently in the wagon.
     *
     * @return The number of luggage items in the wagon.
     */
    public int getLuggageCount() {
        return luggageCount;
    }

    /**
     * Gets the comfort level of the passenger wagon.
     *
     * @return The comfort level of the passenger wagon.
     */
    public int getComfortLevel() {
        return comfortLevel;
    }

    /**
     * Sets the number of passengers in the wagon, if the count is within valid limits.
     *
     * @param count The new count of passengers.
     */
    public void setPassengerCount(int count) {
        if (count >= 0 && count <= CAPACITY - luggageCount) {
            this.passengerCount = count;
        }
    }

    /**
     * Sets the number of luggage items in the wagon, if the count is within valid limits.
     *
     * @param count The new count of luggage items.
     */
    public void setLuggageCount(int count) {
        if (count >= 0 && count <= CAPACITY - passengerCount) {
            this.luggageCount = count;
        }
    }

    /**
     * Returns a string representation of the PassengerWagon object.
     *
     * @return A string representation of the PassengerWagon object.
     */
    @Override
    public String toString() {
        return "Wagon class: " + getClass().getSimpleName() +
                ", passengers count: " + passengerCount + ", luggage count: " + luggageCount +
                ", comfort level: " + comfortLevel;
    }
}
