package com.nikitasutulov.lab8;
import com.nikitasutulov.lab6.trains.PassengerTrain;
import com.nikitasutulov.lab6.wagons.EconomPassengerWagon;
import com.nikitasutulov.lab6.wagons.LuxPassengerWagon;
import com.nikitasutulov.lab6.wagons.MiddlePassengerWagon;
import com.nikitasutulov.lab6.wagons.PassengerWagon;
import com.nikitasutulov.lab7.MyWagonSet;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class PassengerTrainTest {

    private static EconomPassengerWagon wagon1;
    private static MiddlePassengerWagon wagon2;
    private static LuxPassengerWagon wagon3;

    @BeforeAll
    public static void createWagons() throws WagonOverloadedException {
        wagon1 = new EconomPassengerWagon(30, 20);
        wagon2 = new MiddlePassengerWagon(35, 20);
        wagon3 = new LuxPassengerWagon(10, 0);
    }

    @Test
    public void testCalculateTotalPassengersAndLuggage() {
        MyWagonSet<PassengerWagon> wagons = new MyWagonSet<>(Arrays.asList(wagon1, wagon2));
        PassengerTrain train = new PassengerTrain(wagons);

        List<Integer> result = train.calculateTotalPassengersAndLuggage();

        assertEquals(wagon1.getPassengerCount() + wagon2.getPassengerCount(), result.get(0));
        assertEquals(wagon1.getLuggageCount() + wagon2.getLuggageCount(), result.get(1));
    }

    @Test
    public void testSortPassengerWagonsByComfortLevel() {
        MyWagonSet<PassengerWagon> wagons = new MyWagonSet<>(Arrays.asList(wagon1, wagon2, wagon3));
        PassengerTrain train = new PassengerTrain(wagons);

        PassengerWagon[] sortedWagons = train.sortPassengerWagonsByComfortLevel();

        assertEquals(1, sortedWagons[0].getComfortLevel());
        assertEquals(2, sortedWagons[1].getComfortLevel());
        assertEquals(3, sortedWagons[2].getComfortLevel());
    }
}
