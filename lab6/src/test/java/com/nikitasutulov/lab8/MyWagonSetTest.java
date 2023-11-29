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

/**
 * This class contains JUnit tests for the MyWagonSet class, which is a custom collection
 * designed to hold instances of PassengerWagon and its subclasses.
 */
public class MyWagonSetTest {

    private MyWagonSet<PassengerWagon> set;
    private static EconomPassengerWagon wagon1;
    private static MiddlePassengerWagon wagon2;
    private static LuxPassengerWagon wagon3;

    /**
     * Initializes the test setup before each test method.
     */
    @BeforeEach
    public void setup() {
        set = new MyWagonSet<>();
    }

    /**
     * Creates instances of passenger wagons before all test methods.
     */
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

    /**
     * Tests the size() method of the MyWagonSet class.
     */
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

    /**
     * Tests the isEmpty() method of the MyWagonSet class.
     */
    @Test
    public void testIsEmpty() {
        assertTrue(set.isEmpty());
        set = new MyWagonSet<>(wagon1);
        assertFalse(set.isEmpty());

        set.remove(wagon1);
        assertTrue(set.isEmpty());
    }

    /**
     * Tests the contains() method of the MyWagonSet class.
     */
    @Test
    public void testContains() {
        assertFalse(set.contains(wagon1));

        set.add(wagon1);
        assertTrue(set.contains(wagon1));

        set.remove(wagon1);
        assertFalse(set.contains(wagon1));
    }

    /**
     * Tests the iterator() method of the MyWagonSet class.
     */
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

        set = new MyWagonSet<>(List.of(wagon1, wagon2));
        iterator = set.iterator();
        iterator.remove();
        assertFalse(set.contains(wagon1));
    }

    /**
     * Tests the toArray() method of the MyWagonSet class.
     */
    @Test
    public void testToArray() {
        set = new MyWagonSet<>(List.of(wagon1, wagon2, wagon3));

        PassengerWagon[] array = set.toArray(new PassengerWagon[3]);

        assertArrayEquals(new PassengerWagon[]{wagon1, wagon2, wagon3}, array);
    }

    /**
     * Tests the add() method of the MyWagonSet class.
     */
    @Test
    public void testAdd() {
        assertTrue(set.add(wagon1));
        assertTrue(set.contains(wagon1));
        assertFalse(set.add(wagon1));
        assertTrue(set.contains(wagon1));
        assertTrue(set.add(wagon2));
        assertTrue(set.contains(wagon2));
    }

    /**
     * Tests the remove() method of the MyWagonSet class.
     */
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

    /**
     * Tests the addAll() method of the MyWagonSet class.
     */
    @Test
    public void testAddAll() {
        assertTrue(set.addAll(List.of(wagon1, wagon2, wagon3)));
        assertTrue(set.contains(wagon1));
        assertTrue(set.contains(wagon2));
        assertTrue(set.contains(wagon3));
        assertFalse(set.addAll(List.of(wagon1, wagon2, wagon3)));
    }

    /**
     * Tests the removeAll() method of the MyWagonSet class.
     */
    @Test
    public void testRemoveAll() {
        set = new MyWagonSet<>(List.of(wagon1, wagon2, wagon3));
        assertTrue(set.removeAll(List.of(wagon1, wagon3)));
        assertFalse(set.contains(wagon1));
        assertTrue(set.contains(wagon2));
        assertFalse(set.contains(wagon3));
        assertFalse(set.removeAll(List.of(wagon1, wagon3)));
    }

    /**
     * Tests the retainAll() method of the MyWagonSet class.
     */
    @Test
    public void testRetainAll() {
        set = new MyWagonSet<>(List.of(wagon1, wagon2, wagon3));
        assertTrue(set.retainAll(List.of(wagon1, wagon3)));
        assertTrue(set.contains(wagon1));
        assertFalse(set.contains(wagon2));
        assertTrue(set.contains(wagon3));
        assertTrue(set.retainAll(List.of(wagon1, wagon3)));
    }

    /**
     * Tests the containsAll() method of the MyWagonSet class.
     *
     * @throws WagonOverloadedException if an error occurs during wagon creation.
     */
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

    /**
     * Tests the toArray() method with an argument of the MyWagonSet class.
     */
    @Test
    public void testToArrayWithArgument() {
        set = new MyWagonSet<>(List.of(wagon1, wagon2, wagon3));
        PassengerWagon[] array = set.toArray(new PassengerWagon[3]);

        assertArrayEquals(new PassengerWagon[]{wagon1, wagon2, wagon3}, array);
    }

    /**
     * Tests the clear() method of the MyWagonSet class.
     */
    @Test
    public void testClear() {
        set = new MyWagonSet<>(List.of(wagon1, wagon2, wagon3));
        set.clear();
        assertTrue(set.isEmpty());
    }
}