package com.nikitasutulov.lab6.wagons;

public abstract class PassengerWagon extends Wagon {
    private static final int CAPACITY = 60;
    protected int passengerCount;
    protected int luggageCount;
    protected final int comfortLevel;

    public PassengerWagon(int passengerCount, int luggageCount, int comfortLevel) {
        super(CAPACITY);
        if (passengerCount + luggageCount > CAPACITY) {
            throw new IllegalArgumentException("Too many people and luggage to load to a wagon");
        }
        this.passengerCount = passengerCount;
        this.luggageCount = luggageCount;
        this.comfortLevel = comfortLevel;
    }

    public int getPassengerCount() {
        return passengerCount;
    }

    public int getLuggageCount() {
        return luggageCount;
    }

    public int getComfortLevel() {
        return comfortLevel;
    }

    public void setPassengerCount(int count) {
        if (count >= 0 && count <= CAPACITY - luggageCount) {
            this.passengerCount = count;
        }
    }

    public void setLuggageCount(int count) {
        if (count >= 0 && count <= CAPACITY - passengerCount) {
            this.luggageCount = count;
        }
    }

    @Override
    public String toString() {
        return "Wagon class: " + getClass().getSimpleName() +
                ", passengers count: " + passengerCount + ", luggage count: " + luggageCount +
                ", comfort level: " + comfortLevel;
    }
}
