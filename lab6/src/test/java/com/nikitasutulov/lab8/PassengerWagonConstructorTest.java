package com.nikitasutulov.lab8;


import com.nikitasutulov.lab6.wagons.PassengerWagon;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * This class contains JUnit tests for the PassengerWagon class constructor, covering various scenarios.
 * It tests the successful construction and handles cases where the construction is expected to fail
 * due to overloaded conditions or negative arguments.
 */
public class PassengerWagonConstructorTest {

    /**
     * Tests the successful construction of a PassengerWagon instance.
     */
    @Test
    public void testConstructSuccess() {
        try {
            PassengerWagon wagon = new PassengerWagon(20, 10, 3);
        } catch (WagonOverloadedException e) {
            fail();
        }
    }

    /**
     * Tests the construction failure scenarios when the wagon is expected to be overloaded.
     */
    @Test
    public void testConstructFailsOverloaded() {
        WagonOverloadedException e = assertThrows(WagonOverloadedException.class, () -> {
            PassengerWagon wagon = new PassengerWagon(25, 40, 4);
        });
        assertEquals(e.getMessage(), "Too many people and luggage to load to a wagon");

        e = assertThrows(WagonOverloadedException.class, () -> {
            PassengerWagon wagon = new PassengerWagon(60, 15, 4);
        });
        assertEquals(e.getMessage(), "Too many people and luggage to load to a wagon");

        e = assertThrows(WagonOverloadedException.class, () -> {
            PassengerWagon wagon = new PassengerWagon(45, 68, 4);
        });
        assertEquals(e.getMessage(), "Too many people and luggage to load to a wagon");
    }

    /**
     * Tests the construction failure scenarios when negative arguments are provided.
     */
    @Test
    public void testConstructFailsNegativeArguments() {
        IllegalArgumentException e = assertThrows(IllegalArgumentException.class, () -> {
            PassengerWagon wagon = new PassengerWagon(-1, 40, 4);
        });
        assertEquals(e.getMessage(), "Passenger count cannot be less than zero");

        e = assertThrows(IllegalArgumentException.class, () -> {
            PassengerWagon wagon = new PassengerWagon(44, -84, 4);
        });
        assertEquals(e.getMessage(), "Luggage count cannot be less than zero");

        e = assertThrows(IllegalArgumentException.class, () -> {
            PassengerWagon wagon = new PassengerWagon(20, 20, -255);
        });
        assertEquals(e.getMessage(), "Comfort level cannot be less than zero");
    }
}