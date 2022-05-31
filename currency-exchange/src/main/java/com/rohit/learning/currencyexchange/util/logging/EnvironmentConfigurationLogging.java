package com.rohit.learning.currencyexchange.util.logging;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.core.env.AbstractEnvironment;
import org.springframework.core.env.EnumerablePropertySource;
import org.springframework.core.env.Environment;
import org.springframework.core.env.MutablePropertySources;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.stream.StreamSupport;

@Component
public class EnvironmentConfigurationLogging {

    private static final Logger LOGGER = LoggerFactory.getLogger(EnvironmentConfigurationLogging.class);

    @EventListener
    public void logContextRefresh(ContextRefreshedEvent event){
        final Environment environment = event.getApplicationContext().getEnvironment();
        LOGGER.info("============Environment and Configuration============");
        LOGGER.info("Active profile :{}", Arrays.toString(environment.getActiveProfiles()));
        final MutablePropertySources propertySources = ((AbstractEnvironment)environment).getPropertySources();
        StreamSupport.stream(propertySources.spliterator(), false)
                .filter(propertySource -> propertySource instanceof EnumerablePropertySource)
                .map(propertySource -> ((EnumerablePropertySource) propertySource).getPropertyNames())
                .flatMap(Arrays::stream)
                .distinct()
                .forEach(property -> LOGGER.info("{}",property));
        LOGGER.info("=====================================================");

    }
}
