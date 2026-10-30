package com.ruben.bluewave.service;

import com.ruben.bluewave.model.BluewaveException;

public class ServiceException extends BluewaveException {

    public ServiceException(String message) {
        super(message);
    }

    public ServiceException(Throwable cause) {
        super(cause);
    }

    public ServiceException(String message, Throwable cause) {
        super(message, cause);
    }
}
