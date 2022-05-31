package com.rohit.learning.currencyexchange.exception;

public class CurrencyExchangePairNotFoundException extends RuntimeException {

    public CurrencyExchangePairNotFoundException(String message) {
        super(message);
    }
}
