package com.st.java23testingadvanced.exceptions;

/**
 * Exception to handle server unavailability
 *
 * @author Vinod John
 * @Date 03.05.2023
 */
public class ServiceUnavailableException extends Exception {
    private static final long serialVersionID = 1L;

    public ServiceUnavailableException(String message) {
        super(message);
    }
}
