package com.rohit.learning.currencyexchange.restcontroller;

import com.rohit.learning.currencyexchange.entity.CurrencyExchange;
import com.rohit.learning.currencyexchange.exception.CurrencyExchangeExceptionMessages;
import com.rohit.learning.currencyexchange.exception.CurrencyExchangePairNotFoundException;
import com.rohit.learning.currencyexchange.service.CurrencyExchangeService;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

@RestController
@SuppressWarnings("unused")
public class CurrencyExchangeController {

    @Autowired
    private Environment environment;

    @Autowired
    private CurrencyExchangeService currencyExchangeService;

    @GetMapping(path = "/currency-exchange/from/{from}/to/{to}")
    public CurrencyExchange getConversionMultiple(@PathVariable String from, @PathVariable String to) {
        String port = environment.getProperty("local.server.port");
        val currencyExchange = currencyExchangeService.getCurrencyExchange(from,to);
        if(Objects.nonNull(currencyExchange)){
            currencyExchange.setEnvironment(port);
            return currencyExchange;
        }
            throw new CurrencyExchangePairNotFoundException(CurrencyExchangeExceptionMessages.CURRENCY_NOT_FOUND+from+":"+to);
    }

}
