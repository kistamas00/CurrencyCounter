package com.kistamas.dev.demo.currencycounter.resources.configuration;

import com.kistamas.dev.demo.currencycounter.api.CurrencyStock;
import com.kistamas.dev.demo.currencycounter.api.impl.GenericCurrencyCounter;
import com.kistamas.dev.demo.currencycounter.api.impl.GenericCurrencyStock;
import com.kistamas.dev.demo.currencycounter.api.impl.HufCurrencyCounter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

@Configuration
public class CurrencyCounterConfiguration {

    @Value("#{${app.model.currency.stock:T(java.util.Collections).emptyMap()}}")
    Map<String, Map<String, Integer>> initialStock;

    @Bean
    public CurrencyStock createCurrencyStockBean() {
        return new GenericCurrencyStock(initialStock);
    }

    @Bean(name = "CUC")
    public GenericCurrencyCounter createCUCBean() {
        return new GenericCurrencyCounter("CUC", CurrencyStock.CURRENCY_CUC);
    }

    @Bean(name = "HUF")
    public GenericCurrencyCounter createHUFBean() {
        return new GenericCurrencyCounter("HUF", CurrencyStock.CURRENCY_HUF);
    }

    @Bean(name = "HUF2")
    public GenericCurrencyCounter createHUF2Bean() {
        return new HufCurrencyCounter("HUF2", CurrencyStock.CURRENCY_HUF2);
    }
}
