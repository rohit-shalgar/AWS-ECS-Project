package com.rohit.learning.currencyexchange.exception;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

@AllArgsConstructor
@Data
public class CommonExceptionResponse {

    private Date timestamp;
    private String exceptionMessage;
    private String exceptionDetails;
}
