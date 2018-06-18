package com.epam.igor.exception;

public class ServiceException extends Exception {

    /**
     * Constructor call super class's constructor with the same parameters for throwing up
     *
     * @param message including information about exception
     * @param cause   caught exception
     */
    public ServiceException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Constructor call super class's constructor with the same parameters for throwing up
     *
     * @param message including information about exception
     */
    public ServiceException(String message) {
        super(message);
    }
}
