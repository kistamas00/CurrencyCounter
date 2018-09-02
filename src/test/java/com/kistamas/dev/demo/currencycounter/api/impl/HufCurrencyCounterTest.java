package com.kistamas.dev.demo.currencycounter.api.impl;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class HufCurrencyCounterTest {

    @Test
    public void testRoundAmountBeforeCount() {

        // GIVEN
        final HufCurrencyCounter currencyCounter = new HufCurrencyCounter("testCurrency", new String[]{});

        // THEN
        assertEquals(10, currencyCounter.roundAmountBeforeCount(10));
        assertEquals(10, currencyCounter.roundAmountBeforeCount(11));
        assertEquals(10, currencyCounter.roundAmountBeforeCount(12));
        assertEquals(15, currencyCounter.roundAmountBeforeCount(13));
        assertEquals(15, currencyCounter.roundAmountBeforeCount(14));
        assertEquals(15, currencyCounter.roundAmountBeforeCount(15));
    }
}