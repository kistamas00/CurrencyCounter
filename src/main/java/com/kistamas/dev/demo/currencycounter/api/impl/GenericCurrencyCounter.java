package com.kistamas.dev.demo.currencycounter.api.impl;

import com.kistamas.dev.demo.currencycounter.api.CurrencyCounter;
import com.kistamas.dev.demo.currencycounter.api.CurrencyStock;

import javax.inject.Inject;
import java.util.*;
import java.util.stream.Collectors;

public class GenericCurrencyCounter implements CurrencyCounter {

    protected String currency;
    protected List<String> bankNotes;
    @Inject
    private CurrencyStock stock;

    public GenericCurrencyCounter(final String currency, final String[] bankNotes) {

        List<String> notes = Arrays.asList(bankNotes);
        notes.sort(Comparator.comparingInt(Integer::parseInt));
        Collections.reverse(notes);

        this.currency = currency;
        this.bankNotes = Collections.unmodifiableList(notes);
    }

    protected int roundAmountBeforeCount(final int amount) {
        return amount;
    }

    protected List<String> getPossibleNotes(final int amount) {
        return bankNotes.stream().filter(b -> Integer.parseInt(b) <= amount).collect(Collectors.toList());
    }

    protected Map<String, Integer> removeNotesFromStock(final Map<String, Integer> stock, final String note, final int count) {

        final Integer value = stock.get(note);
        if (value == null) {
            return stock;
        }
        if (count > value) {
            throw new IllegalArgumentException("Count can't be larger than value!");
        }

        Map<String, Integer> result = new HashMap<>(stock);
        result.put(note, value - count);
        return result;
    }

    protected Map<String, Integer> countNotes(final Map<String, Integer> stock, final int amount) {

        if (amount == 0) {
            return new HashMap<>();
        }

        List<String> possibleNotes = getPossibleNotes(amount);

        for (String note : possibleNotes) {

            int maxCount = 0;
            if (stock.get(note) != null) {
                maxCount = stock.get(note);
            } else {
                maxCount = amount / Integer.parseInt(note);
            }

            for (int i = maxCount; i > 0; i--) {

                final Map<String, Integer> newStock = removeNotesFromStock(stock, note, i);
                final int newAmount = amount - i * Integer.parseInt(note);
                final Map<String, Integer> result = countNotes(newStock, newAmount);
                if (result != null) {
                    result.put(note, i);
                    return result;
                }
            }
        }

        return null;
    }

    @Override
    public Map<String, Integer> countCurrency(int amount) {

        Map<String, Integer> result = countNotes(getStock(), roundAmountBeforeCount(amount));

        if (result == null) {

            return Collections.emptyMap();

        } else {

            stock.removeNotesFromStock(currency, result);
            return result;
        }
    }

    @Override
    public Map<String, Integer> getStock() {

        return stock.getStock(currency);
    }
}