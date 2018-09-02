package com.kistamas.dev.demo.currencycounter.api;

import java.util.Map;

public interface CurrencyStock {

    String[] CURRENCY_CUC
            = new String[]{"1", "3", "5", "10", "20", "50", "100"};
    String[] CURRENCY_HUF
            = new String[]{"1", "2", "5", "10", "20", "50", "100", "200", "500", "1000", "2000", "5000", "10000", "20000"};
    String[] CURRENCY_HUF2
            = new String[]{"5", "10", "20", "50", "100", "200", "500", "1000", "2000", "5000", "10000", "20000"};

    Map<String, Integer> getStock(String currency);

    void removeNotesFromStock(String currency, Map<String, Integer> notes);
}
