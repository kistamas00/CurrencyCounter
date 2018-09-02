package com.kistamas.dev.demo.currencycounter.api.impl;

import com.kistamas.dev.demo.currencycounter.api.CurrencyStock;

import javax.inject.Singleton;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@Singleton
public class GenericCurrencyStock implements CurrencyStock {

    private Map<String, Map<String, Integer>> stocks;

    public GenericCurrencyStock(Map<String, Map<String, Integer>> initialStock) {

        Map<String, Map<String, Integer>> copy = new HashMap<>();
        for (String currency : initialStock.keySet()) {
            Map<String, Integer> count = new HashMap<>(initialStock.get(currency));
            copy.put(currency, count);
        }

        this.stocks = copy;
    }

    @Override
    public Map<String, Integer> getStock(final String currency) {

        Map<String, Integer> result = stocks.get(currency);

        if (result == null) {
            return Collections.emptyMap();
        } else {
            return Collections.unmodifiableMap(stocks.get(currency));
        }
    }

    @Override
    public void removeNotesFromStock(final String currency, final Map<String, Integer> notes) {

        Map<String, Integer> stock = stocks.get(currency);

        if (stock == null || notes.isEmpty()) {
            return;
        }

        Map<String, Integer> newValues = new HashMap<>();
        for (String note : notes.keySet()) {
            final int decreaseValue = notes.get(note);
            final Integer currentCount = stock.get(note);
            if (currentCount == null) {
                continue;
            } else if (currentCount < decreaseValue) {
                throw new IllegalArgumentException("Decrease value cant be larger than current value!");
            }
            newValues.put(note, currentCount - decreaseValue);
        }

        stock.putAll(newValues);
    }
}
