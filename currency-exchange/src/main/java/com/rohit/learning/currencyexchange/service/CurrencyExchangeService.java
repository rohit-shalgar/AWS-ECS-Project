package com.rohit.learning.currencyexchange.service;

import com.rohit.learning.currencyexchange.entity.CurrencyExchange;

public interface CurrencyExchangeService {

    CurrencyExchange getCurrencyExchange(String fromCurrency, String toCurrency);
}
