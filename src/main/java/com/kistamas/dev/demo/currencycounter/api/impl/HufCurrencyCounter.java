package com.kistamas.dev.demo.currencycounter.api.impl;

public class HufCurrencyCounter extends GenericCurrencyCounter {

    public HufCurrencyCounter(String currency, String[] bankNote) {
        super(currency, bankNote);
    }

    @Override
    protected int roundAmountBeforeCount(int amount) {
        return (int) Math.round(amount / 5d) * 5;
    }
}
