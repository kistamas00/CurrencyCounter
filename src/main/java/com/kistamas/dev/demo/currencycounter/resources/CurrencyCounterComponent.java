package com.kistamas.dev.demo.currencycounter.resources;

import com.kistamas.dev.demo.currencycounter.api.CurrencyCounter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.Map;

@Component
@Path("/")
public class CurrencyCounterComponent {

    private static final Logger LOGGER = LoggerFactory.getLogger(CurrencyCounterComponent.class);

    @Inject
    private ApplicationContext applicationContext;

    private CurrencyCounter getCurrencyCounterBeanByName(final String name) {

        CurrencyCounter bean = null;

        try {
            bean = applicationContext.getBean(name, CurrencyCounter.class);
        } catch (NoSuchBeanDefinitionException e) {
            LOGGER.warn("Can't found bean named '" + name + "'", e);
        }

        return bean;
    }

    @GET
    @Path("/count/{currency}/{amount}")
    @Produces(MediaType.APPLICATION_JSON)
    public Map<String, Integer> getCount(@PathParam("currency") String currency, @PathParam("amount") Integer amount) {

        CurrencyCounter bean = getCurrencyCounterBeanByName(currency);

        if (bean == null) {
            return null;
        } else {
            return bean.countCurrency(amount);
        }
    }

    @GET
    @Path("/stock/{currency}")
    @Produces(MediaType.APPLICATION_JSON)
    public Map<String, Integer> getStock(@PathParam("currency") String currency) {

        CurrencyCounter bean = getCurrencyCounterBeanByName(currency);

        if (bean == null) {
            return null;
        } else {
            return bean.getStock();
        }
    }
}
