package com.rohit.learning.currencyexchange.configuration;

import com.amazonaws.xray.javax.servlet.AWSXRayServletFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Filter;

@Configuration
public class AwsXRayConfig {

    @Bean
    public Filter tracingFilter(){
        return new AWSXRayServletFilter("currency-exchange-service");
    }
}
