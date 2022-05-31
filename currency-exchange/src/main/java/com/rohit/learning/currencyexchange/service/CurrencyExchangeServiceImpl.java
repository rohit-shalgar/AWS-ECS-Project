package com.rohit.learning.currencyexchange.service;

import com.rohit.learning.currencyexchange.entity.CurrencyExchange;
import com.rohit.learning.currencyexchange.repository.CurrencyExchangeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@SuppressWarnings("unused")
public class CurrencyExchangeServiceImpl implements CurrencyExchangeService{

    @Autowired
    private CurrencyExchangeRepository currencyExchangeRepository;

    @Override
    public CurrencyExchange getCurrencyExchange(String fromCurrency, String toCurrency) {
       return currencyExchangeRepository.findByFromAndTo(fromCurrency,toCurrency);
    }
}
