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
    public void testSizeEmpty() {
        assertEquals(0, set.size());
    }

    @Test
    public void testSizeOneElement() {
        set = new MyWagonSet<>(wagon1);
        assertEquals(1, set.size());
    }

    @Test
    public void testSizeTwoElements() {
        set = new MyWagonSet<>(List.of(wagon1, wagon2));
        assertEquals(2, set.size());
    }

    @Test
    public void testSizeIncreasesWithAdding() {
        set = new MyWagonSet<>(List.of(wagon1, wagon2));
        assertEquals(2, set.size());
        set.add(wagon3);
        assertEquals(3, set.size());
    }

    @Test
    public void testSizeDecreasesWithRemoving() {
        set = new MyWagonSet<>(List.of(wagon1, wagon2));
        assertEquals(2, set.size());
        set.remove(wagon2);
        assertEquals(1, set.size());
    }

    /**
     * Tests the isEmpty() method of the MyWagonSet class.
     */
    @Test
    public void testSetWithNoElementsIsEmpty() {
        assertTrue(set.isEmpty());
    }

    @Test
    public void testSetWithElementsIsNotEmpty() {
        set = new MyWagonSet<>(wagon1);
        assertFalse(set.isEmpty());
    }

    /**
     * Tests the contains() method of the MyWagonSet class.
     */
    @Test
    public void testEmptySetDoesNotContainElement() {
        assertFalse(set.contains(wagon1));
    }

    @Test
    public void testSetWithElementContains() {
        set = new MyWagonSet<>(wagon1);
        assertTrue(set.contains(wagon1));
    }

    /**
     * Tests the iterator() method of the MyWagonSet class.
     */
    @Test
    public void testIteratorOfEmptySetDoesNotHaveNext() {
        Iterator<PassengerWagon> iterator = set.iterator();
        assertFalse(iterator.hasNext());
    }

    @Test
    public void testIteratorOfNonEmptySetHasNext() {
        set = new MyWagonSet<>(wagon1);
        Iterator<PassengerWagon> iterator = set.iterator();

        assertTrue(iterator.hasNext());
        assertEquals(wagon1, iterator.next());
    }

    @Test
    public void testIteratorOfSetWithTwoElementsHasNextTwice() {
        set = new MyWagonSet<>(List.of(wagon1, wagon2));
        Iterator<PassengerWagon> iterator = set.iterator();
        assertTrue(iterator.hasNext());
        assertEquals(wagon1, iterator.next());
        assertTrue(iterator.hasNext());
        assertEquals(wagon2, iterator.next());
    }

    @Test
    public void testIteratorThrowsWhenNoNext() {
        set = new MyWagonSet<>(List.of(wagon1, wagon2));
        Iterator<PassengerWagon> iterator = set.iterator();
        iterator.next();
        iterator.next();
        assertFalse(iterator.hasNext());
        NoSuchElementException e = assertThrows(NoSuchElementException.class, iterator::next);
        assertEquals(e.getMessage(), "No more elements in the set");
    }

    @Test
    public void testIteratorRemoveMethodRemovesNext() {
        set = new MyWagonSet<>(List.of(wagon1, wagon2));
        Iterator<PassengerWagon> iterator = set.iterator();
        iterator.remove();
        assertFalse(set.contains(wagon1));
        assertTrue(set.contains(wagon2));
    }

    /**
     * Tests the toArray() method of the MyWagonSet class.
     */
    @Test
    public void testEmptySetToArrayIsEmptyArray() {
        PassengerWagon[] array = set.toArray(new PassengerWagon[0]);
        assertArrayEquals(new PassengerWagon[0], array);
    }

    @Test
    public void testNonEmptySetToArray() {
        set = new MyWagonSet<>(List.of(wagon1, wagon2, wagon3));

        PassengerWagon[] array = set.toArray(new PassengerWagon[3]);

        assertArrayEquals(new PassengerWagon[]{wagon1, wagon2, wagon3}, array);
    }

    /**
     * Tests the add() method of the MyWagonSet class.
     */
    @Test
    public void testAddedElementIsContained() {
        assertTrue(set.add(wagon1));
        assertTrue(set.contains(wagon1));
    }

    @Test
    public void testCannotAddTheSameElementTwice() {
        assertTrue(set.add(wagon1));
        assertTrue(set.contains(wagon1));
        assertFalse(set.add(wagon1));
        assertTrue(set.contains(wagon1));
    }

    /**
     * Tests the remove() method of the MyWagonSet class.
     */
    @Test
    public void testSetDoesNotContainRemovedElement() {
        set = new MyWagonSet<>(List.of(wagon1, wagon2));
        assertTrue(set.remove(wagon1));
        assertFalse(set.contains(wagon1));
    }

    @Test
    public void testCannotRemoveTheSameElementTwice() {
        set = new MyWagonSet<>(List.of(wagon1, wagon2));
        assertTrue(set.remove(wagon1));
        assertFalse(set.contains(wagon1));
        assertFalse(set.remove(wagon1));
        assertFalse(set.contains(wagon1));
    }

    @Test
    public void testRemovingLastElementMakesSetEmpty() {
        set = new MyWagonSet<>(wagon1);
        assertTrue(set.remove(wagon1));
        assertTrue(set.isEmpty());
    }

    /**
     * Tests the addAll() method of the MyWagonSet class.
     */
    @Test
    public void testAddAllWithEmptyCollectionChangesNothing() {
        assertTrue(set.addAll(List.of()));
        assertArrayEquals(new MyWagonSet<>().toArray(), set.toArray());
    }

    @Test
    public void testAddAllAddsAllTheElementsOfCollection() {
        assertTrue(set.addAll(List.of(wagon1, wagon2, wagon3)));
        assertTrue(set.contains(wagon1));
        assertTrue(set.contains(wagon2));
        assertTrue(set.contains(wagon3));
        assertFalse(set.addAll(List.of(wagon1, wagon2, wagon3)));
    }
    @Test
    public void testCannotAddAllIfAnyElementIsAlreadyAdded() {
        set.addAll(List.of(wagon1, wagon2, wagon3));
        assertTrue(set.contains(wagon1));
        assertTrue(set.contains(wagon2));
        assertTrue(set.contains(wagon3));
        assertFalse(set.addAll(List.of(wagon1, wagon2, wagon3)));
    }

    /**
     * Tests the removeAll() method of the MyWagonSet class.
     */
    @Test
    public void testRemoveAllWithEmptyCollectionChangesNothing() {
        assertTrue(set.removeAll(List.of()));
        assertArrayEquals(new MyWagonSet<>().toArray(), set.toArray());
    }

    @Test
    public void testSetDoesNotHaveElementsRemovedWithRemoveAll() {
        set = new MyWagonSet<>(List.of(wagon1, wagon2, wagon3));
        assertTrue(set.removeAll(List.of(wagon1, wagon3)));
        assertFalse(set.contains(wagon1));
        assertTrue(set.contains(wagon2));
        assertFalse(set.contains(wagon3));
        assertFalse(set.removeAll(List.of(wagon1, wagon3)));
    }

    @Test
    public void testCannotRemoveAllWithRemovedElements() {
        set = new MyWagonSet<>(List.of(wagon1, wagon2, wagon3));
        set.removeAll(List.of(wagon1, wagon3));
        assertFalse(set.removeAll(List.of(wagon1, wagon3)));
    }

    /**
     * Tests the retainAll() method of the MyWagonSet class.
     */
    @Test
    public void testRetainAllWithEmptyCollectionClearsSet() {
        set = new MyWagonSet<>(List.of(wagon1, wagon2, wagon3));
        assertTrue(set.retainAll(List.of()));
        assertTrue(set.isEmpty());
    }

    @Test
    public void testRetainAllLeavesOnlyElementsOfCollectionInSet() {
        set = new MyWagonSet<>(List.of(wagon1, wagon2, wagon3));
        assertTrue(set.retainAll(List.of(wagon1, wagon3)));
        assertTrue(set.contains(wagon1));
        assertFalse(set.contains(wagon2));
        assertTrue(set.contains(wagon3));
    }

    /**
     * Tests the containsAll() method of the MyWagonSet class.
     *
     * @throws WagonOverloadedException if an error occurs during wagon creation.
     */
    @Test
    public void testSetContainsAllOfEmptyCollection() {
        set = new MyWagonSet<>(List.of(wagon1, wagon2, wagon3));
        assertTrue(set.containsAll(List.of()));
    }

    @Test
    public void testSetContainsAllOfCollectionWithOneItsElement() {
        set = new MyWagonSet<>(List.of(wagon1, wagon2, wagon3));
        assertTrue(set.containsAll(List.of(wagon1)));
    }

    @Test
    public void testSetContainsAllOfCollectionWithManyItsElements() {
        set = new MyWagonSet<>(List.of(wagon1, wagon2, wagon3));
        assertTrue(set.containsAll(List.of(wagon1, wagon2)));
    }

    @Test
    public void testSetContainsAllOfCollectionWithAllItsElements() {
        set = new MyWagonSet<>(List.of(wagon1, wagon2, wagon3));
        assertTrue(set.containsAll(List.of(wagon1, wagon2, wagon3)));
    }

    @Test
    public void testContainsAllReturnsFalseIfCollectionHasElementSetDoesNotContain() throws WagonOverloadedException {
        set = new MyWagonSet<>(List.of(wagon1, wagon2, wagon3));
        LuxPassengerWagon wagon4 = new LuxPassengerWagon(10, 10);
        assertFalse(set.containsAll(List.of(wagon4)));
    }

    @Test
    public void testContainsAllReturnsFalseIfCollectionHasElementSetDoesNotContainAmongElementsItContains() throws WagonOverloadedException {
        set = new MyWagonSet<>(List.of(wagon1, wagon2, wagon3));
        LuxPassengerWagon wagon4 = new LuxPassengerWagon(10, 10);
        assertFalse(set.containsAll(List.of(wagon1, wagon2, wagon3, wagon4)));
    }

    /**
     * Tests the toArray() method without an argument.
     */
    @Test
    public void testEmptySetToArrayWithoutArgumentIsEmptyArray() {
        Object[] array = set.toArray();
        assertArrayEquals(new Object[0], array);
    }

    @Test
    public void testNonEmptySetWithoutArgumentToArray() {
        set = new MyWagonSet<>(List.of(wagon1, wagon2, wagon3));

        Object[] array = set.toArray();
        assertArrayEquals(new Object[]{wagon1, wagon2, wagon3}, array);
    }

    /**
     * Tests the clear() method of the MyWagonSet class.
     */
    @Test
    public void testClearingEmptySetLeavesItEmpty() {
        set.clear();
        assertTrue(set.isEmpty());
    }

    @Test
    public void testClearingSetWithOneElementMakesItEmpty() {
        set = new MyWagonSet<>(wagon1);
        set.clear();
        assertTrue(set.isEmpty());
    }

    @Test
    public void testClearingSetWithManyElementsMakesItEmpty() {
        set = new MyWagonSet<>(List.of(wagon1, wagon2, wagon3));
        set.clear();
        assertTrue(set.isEmpty());
    }
}