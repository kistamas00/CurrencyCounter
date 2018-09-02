package com.kistamas.dev.demo.currencycounter.api;

import java.util.Map;

public interface CurrencyCounter {

    Map<String, Integer> countCurrency(int amount);

    Map<String, Integer> getStock();
}
