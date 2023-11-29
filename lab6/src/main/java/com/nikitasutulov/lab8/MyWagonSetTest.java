package com.nikitasutulov.lab8;

import com.nikitasutulov.lab6.wagons.EconomPassengerWagon;
import com.nikitasutulov.lab6.wagons.LuxPassengerWagon;
import com.nikitasutulov.lab6.wagons.MiddlePassengerWagon;
import com.nikitasutulov.lab6.wagons.PassengerWagon;
import com.nikitasutulov.lab7.MyWagonSet;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

public class MyWagonSetTest {

    private MyWagonSet<PassengerWagon> set;
    private static EconomPassengerWagon wagon1;
    private static MiddlePassengerWagon wagon2;
    private static LuxPassengerWagon wagon3;

    @BeforeEach
    public void setup() {
        set = new MyWagonSet<>();
    }

    @BeforeAll
    public static void createWagons() {
        try {
            wagon1 = new EconomPassengerWagon(30, 20);
            wagon2 = new MiddlePassengerWagon(35, 20);
            wagon3 = new LuxPassengerWagon(10, 0);
        } catch (WagonOverloadedException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void testSize() {
        assertEquals(0, set.size());


        set = new MyWagonSet<>(List.of(wagon1, wagon2));
        assertEquals(2, set.size());

        set.remove(wagon1);
        assertEquals(1, set.size());

        set.remove(wagon2);
        assertEquals(0, set.size());
    }

    @Test
    public void testIsEmpty() {
        assertTrue(set.isEmpty());
        set = new MyWagonSet<>(wagon1);
        assertFalse(set.isEmpty());

        set.remove(wagon1);
        assertTrue(set.isEmpty());
    }

    @Test
    public void testContains() {
        assertFalse(set.contains(wagon1));

        set.add(wagon1);
        assertTrue(set.contains(wagon1));

        set.remove(wagon1);
        assertFalse(set.contains(wagon1));
    }

    @Test
    public void testIterator() {
        set = new MyWagonSet<>(List.of(wagon1, wagon2));
        Iterator<PassengerWagon> iterator = set.iterator();

        assertTrue(iterator.hasNext());
        assertEquals(wagon1, iterator.next());

        assertTrue(iterator.hasNext());
        assertEquals(wagon2, iterator.next());

        assertFalse(iterator.hasNext());
        NoSuchElementException e = assertThrows(NoSuchElementException.class, iterator::next);
        assertEquals(e.getMessage(), "No more elements in the set");
    }

    @Test
    public void testToArray() {
        set = new MyWagonSet<>(List.of(wagon1, wagon2, wagon3));

        PassengerWagon[] array = set.toArray(new PassengerWagon[3]);

        assertArrayEquals(new PassengerWagon[]{wagon1, wagon2, wagon3}, array);
    }

    @Test
    public void testAdd() {
        assertTrue(set.add(wagon1));
        assertTrue(set.contains(wagon1));
        assertFalse(set.add(wagon1));
        assertTrue(set.contains(wagon1));
        assertTrue(set.add(wagon2));
        assertTrue(set.contains(wagon2));
    }

    @Test
    public void testRemove() {
        set = new MyWagonSet<>(List.of(wagon1, wagon2));
        assertTrue(set.remove(wagon1));
        assertFalse(set.contains(wagon1));
        assertFalse(set.remove(wagon1));
        assertFalse(set.contains(wagon1));
        assertTrue(set.remove(wagon2));
        assertFalse(set.contains(wagon2));
    }

    @Test
    public void testAddAll() {
        assertTrue(set.addAll(List.of(wagon1, wagon2, wagon3)));
        assertTrue(set.contains(wagon1));
        assertTrue(set.contains(wagon2));
        assertTrue(set.contains(wagon3));
        assertFalse(set.addAll(List.of(wagon1, wagon2, wagon3)));
    }

    @Test
    public void testRemoveAll() {
        set = new MyWagonSet<>(List.of(wagon1, wagon2, wagon3));
        assertTrue(set.removeAll(List.of(wagon1, wagon3)));
        assertFalse(set.contains(wagon1));
        assertTrue(set.contains(wagon2));
        assertFalse(set.contains(wagon3));
        assertFalse(set.removeAll(List.of(wagon1, wagon3)));
    }

    @Test
    public void testRetainAll() {
        set = new MyWagonSet<>(List.of(wagon1, wagon2, wagon3));
        assertTrue(set.retainAll(List.of(wagon1, wagon3)));
        assertTrue(set.contains(wagon1));
        assertFalse(set.contains(wagon2));
        assertTrue(set.contains(wagon3));
        assertTrue(set.retainAll(List.of(wagon1, wagon3)));
    }

    @Test
    public void testContainsAll() throws WagonOverloadedException {
        set = new MyWagonSet<>(List.of(wagon1, wagon2, wagon3));
        assertTrue(set.containsAll(List.of(wagon1)));
        assertTrue(set.containsAll(List.of(wagon1, wagon2)));
        assertTrue(set.containsAll(List.of(wagon1, wagon2, wagon3)));
        LuxPassengerWagon wagon4 = new LuxPassengerWagon(10, 10);
        assertFalse(set.containsAll(List.of(wagon4)));
        assertFalse(set.containsAll(List.of(wagon1, wagon2, wagon3, wagon4)));
    }

    @Test
    public void testToArrayWithArgument() {
        set = new MyWagonSet<>(List.of(wagon1, wagon2, wagon3));
        PassengerWagon[] array = set.toArray(new PassengerWagon[3]);

        assertArrayEquals(new PassengerWagon[]{wagon1, wagon2, wagon3}, array);
    }

    @Test
    public void testClear() {
        set = new MyWagonSet<>(List.of(wagon1, wagon2, wagon3));
        set.clear();
        assertTrue(set.isEmpty());
    }
}
