package com.rohit.learning.currencyexchange;

import com.rohit.learning.currencyexchange.entity.CurrencyExchange;
import com.rohit.learning.currencyexchange.repository.CurrencyExchangeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.math.BigDecimal;

@SpringBootApplication
public class CurrencyExchangeServiceApplication implements CommandLineRunner {

	@Autowired
	private CurrencyExchangeRepository repository;

	public static void main(String[] args) {
		SpringApplication.run(CurrencyExchangeServiceApplication.class, args);
	}


	@Override
	public void run(String... args) throws Exception {

		if(repository.count() == 0){
			repository.save(new CurrencyExchange(10001, "USD", "INR", BigDecimal.valueOf(60),""));
			repository.save(new CurrencyExchange(10002, "EUR", "INR", BigDecimal.valueOf(70),""));
			repository.save(new CurrencyExchange(10003, "AUD", "INR", BigDecimal.valueOf(20),""));
		}

	}
}
