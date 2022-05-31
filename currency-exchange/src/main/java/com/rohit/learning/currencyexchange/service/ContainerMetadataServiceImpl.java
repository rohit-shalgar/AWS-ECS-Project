package com.rohit.learning.currencyexchange.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ContainerMetadataServiceImpl implements ContainerMetadataService {

    private static final String ENVIRONMENT_VARIABLE_ECS_CONTAINER_METADATA_URI = "ECS_CONTAINER_METADATA_URI";

    private static final String DEFAULT_VALUE = "EMPTY";

    private static final Logger LOGGER = LoggerFactory.getLogger(ContainerMetadataServiceImpl.class);

    @Value("${" +ENVIRONMENT_VARIABLE_ECS_CONTAINER_METADATA_URI+":"+DEFAULT_VALUE+ "}")
    private String containerMetadataUrl;

    @Override
    public String retrieveContainerMetadataInfo() {

        if (containerMetadataUrl.contains(DEFAULT_VALUE)) {
            LOGGER.info("Environment Variable Not Available - " + ENVIRONMENT_VARIABLE_ECS_CONTAINER_METADATA_URI);
            return "NA";
        }

        return new RestTemplate().getForObject(containerMetadataUrl, String.class);
    }




}
