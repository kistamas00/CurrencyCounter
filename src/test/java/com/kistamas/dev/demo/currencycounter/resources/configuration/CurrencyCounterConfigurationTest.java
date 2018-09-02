package com.kistamas.dev.demo.currencycounter.resources.configuration;

import com.kistamas.dev.demo.currencycounter.api.CurrencyStock;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class CurrencyCounterConfigurationTest {

    @InjectMocks
    CurrencyCounterConfiguration currencyCounterConfiguration;
    @Mock
    Map<String, Map<String, Integer>> initialStock;

    @Test
    public void testCurrencyStock() {

        // GIVEN
        Map<String, Integer> stock = new HashMap<String, Integer>() {{
            put("2", 4);
        }};
        Mockito.when(initialStock.keySet()).thenReturn(Stream.of("testCurrency").collect(Collectors.toSet()));
        Mockito.when(initialStock.get("testCurrency")).thenReturn(stock);

        // WHEN
        CurrencyStock currencyStockBean = currencyCounterConfiguration.createCurrencyStockBean();

        // THEN
        assertEquals(stock, currencyStockBean.getStock("testCurrency"));
    }
}