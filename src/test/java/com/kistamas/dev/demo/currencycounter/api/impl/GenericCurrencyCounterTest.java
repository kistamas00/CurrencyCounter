package com.kistamas.dev.demo.currencycounter.api.impl;

import org.junit.Test;

import java.util.*;

import static org.junit.Assert.*;

public class GenericCurrencyCounterTest {

    @Test
    public void testCreateCurrencyCounter() {

        // GIVEN
        final String[] testNotes = new String[]{"2", "1", "3"};
        final String[] orderedTestNotes = new String[]{"3", "2", "1"};

        // WHEN
        final GenericCurrencyCounter currencyCounter = new GenericCurrencyCounter("testCurrency", testNotes);

        // THEN
        assertEquals(Arrays.asList(orderedTestNotes), currencyCounter.bankNotes);
    }

    @Test
    public void testGetPossibleNotes() {

        // GIVEN
        final GenericCurrencyCounter currencyCounter = new GenericCurrencyCounter("testCurrency", new String[]{"2", "1", "3"});

        // WHEN
        final List<String> possibleNotes = currencyCounter.getPossibleNotes(2);

        // THEN
        assertEquals(Arrays.asList("2", "1"), possibleNotes);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testRemoveNotesFromStockException() {

        // GIVEN
        final GenericCurrencyCounter currencyCounter = new GenericCurrencyCounter("testCurrency", new String[]{"2", "1", "3"});
        final Map<String, Integer> testCurrency = new HashMap<>();
        testCurrency.put("x", 100);

        // WHEN
        currencyCounter.removeNotesFromStock(testCurrency, "x", 101);

        // THEN
        fail();
    }

    @Test
    public void testRemoveNotesFromStock() {

        // GIVEN
        final GenericCurrencyCounter currencyCounter = new GenericCurrencyCounter("testCurrency", new String[]{"2", "1", "3"});
        final Map<String, Integer> testCurrency = new HashMap<>();
        testCurrency.put("x", 100);

        // WHEN
        Map<String, Integer> result = currencyCounter.removeNotesFromStock(testCurrency, "x", 20);

        // THEN
        assertEquals(80, (int) result.get("x"));
    }

    @Test
    public void testCountNotesNull() {

        //GIVEN
        final GenericCurrencyCounter currencyCounter = new GenericCurrencyCounter("testCurrency", new String[]{"5", "3"});

        // THEN
        assertNull(currencyCounter.countNotes(Collections.emptyMap(), 1));
        assertNull(currencyCounter.countNotes(Collections.emptyMap(), 7));
    }

    @Test
    public void testCountNotes() {

        //GIVEN
        final GenericCurrencyCounter currencyCounter = new GenericCurrencyCounter("testCurrency", new String[]{"5", "3"});
        Map<String, Integer> expected = new HashMap<>();
        expected.put("5", 1);
        expected.put("3", 2);

        // WHEN
        Map<String, Integer> result = currencyCounter.countNotes(Collections.emptyMap(), 11);

        // THEN
        assertEquals(expected, result);
    }
}