package com.github.sixro.openinsider4j;

/**
 * Represents an exception thrown when a ticker cannot be resolved.
 */
public class NoSuchTickerException extends RuntimeException {

    /**
     * Creates this exception using specified message.
     *
     * @param message the message
     */
    public NoSuchTickerException(String message) {
        super(message);
    }

    /**
     * Creates this exception using specified message and cause.
     *
     * @param message the message
     * @param cause the cause
     */
    public NoSuchTickerException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Creates this exception using specified cause.
     *
     * @param cause the cause
     */
    public NoSuchTickerException(Throwable cause) {
        super(cause);
    }
}
