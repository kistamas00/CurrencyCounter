package com.kistamas.dev.demo.currencycounter.api.impl;

import org.junit.Test;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

public class GenericCurrencyStockTest {

    @Test(expected = IllegalArgumentException.class)
    public void testRemoveNotesFromStockException() {

        // GIVEN
        final Map<String, Map<String, Integer>> initialStock = new HashMap<>();
        final Map<String, Integer> testCurrency = new HashMap<>();
        testCurrency.put("x", 100);
        initialStock.put("testCurrency", testCurrency);
        GenericCurrencyStock stocks = new GenericCurrencyStock(initialStock);
        testCurrency.put("x", 101);

        // WHEN
        stocks.removeNotesFromStock("testCurrency", testCurrency);

        // THEN
        fail();
    }

    @Test
    public void testRemoveNotesFromStock() {

        // GIVEN
        final Map<String, Map<String, Integer>> initialStock = new HashMap<>();
        final Map<String, Integer> testCurrency = new HashMap<>();
        testCurrency.put("x", 100);
        initialStock.put("testCurrency", testCurrency);
        GenericCurrencyStock stocks = new GenericCurrencyStock(initialStock);
        testCurrency.put("x", 10);

        // WHEN
        stocks.removeNotesFromStock("testCurrency", testCurrency);

        // THEN
        assertEquals(90, (int) stocks.getStock("testCurrency").get("x"));
    }

    @Test
    public void testStockCreationWithNoInitialStock() {

        // GIVEN
        final GenericCurrencyStock stocks = new GenericCurrencyStock(Collections.emptyMap());

        // WHEN
        Map<String, Integer> stock = stocks.getStock("testCurrency");

        // THEN
        assertTrue(stock.isEmpty());
    }

    @Test
    public void testStockCreationWithInitialStock() {

        // GIVEN
        final Map<String, Map<String, Integer>> initialStock = new HashMap<>();
        final Map<String, Integer> testCurrency = new HashMap<>();
        testCurrency.put("x", 100);
        initialStock.put("testCurrency", testCurrency);
        final GenericCurrencyStock stocks = new GenericCurrencyStock(initialStock);
        testCurrency.put("x", 50);

        // WHEN
        Map<String, Integer> stock = stocks.getStock("testCurrency");

        // THEN
        assertEquals(100, (int) stock.get("x"));
        assertEquals(50, (int) initialStock.get("testCurrency").get("x"));
    }
}