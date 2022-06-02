package com.rohit.learning.currencyexchange.restcontroller;

import com.amazonaws.xray.spring.aop.XRayEnabled;
import com.rohit.learning.currencyexchange.entity.CurrencyExchange;
import com.rohit.learning.currencyexchange.exception.CurrencyExchangeExceptionMessages;
import com.rohit.learning.currencyexchange.exception.CurrencyExchangePairNotFoundException;
import com.rohit.learning.currencyexchange.service.ContainerMetadataService;
import com.rohit.learning.currencyexchange.service.CurrencyExchangeService;
import lombok.val;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.Objects;

@RestController
@XRayEnabled
@SuppressWarnings("unused")
public class CurrencyExchangeController {

    private static final Logger LOGGER = LoggerFactory.getLogger(CurrencyExchangeController.class);

    @Autowired
    private ContainerMetadataService metadataService;

    @Autowired
    private CurrencyExchangeService currencyExchangeService;

    @GetMapping(path = "/currency-exchange/from/{from}/to/{to}")
    public CurrencyExchange getConversionMultiple(@PathVariable String from,
                                                  @PathVariable String to,
                                                  @RequestHeader Map<String,String> headers) {
        printAllHeaders(headers);
        val currencyExchange = currencyExchangeService.getCurrencyExchange(from, to);
        if (Objects.nonNull(currencyExchange)) {
            currencyExchange.setEnvironment(metadataService.retrieveContainerMetadataInfo());
            return currencyExchange;
        }
        throw new CurrencyExchangePairNotFoundException(CurrencyExchangeExceptionMessages.CURRENCY_NOT_FOUND + from + ":" + to);
    }

    private void printAllHeaders(Map<String, String> headers) {
        for (Map.Entry<String,String> headerEntry:headers.entrySet()){
            LOGGER.info(String.format("Header - '%s' - '%s'",headerEntry.getKey(),headerEntry.getValue()));
        }
    }

}
