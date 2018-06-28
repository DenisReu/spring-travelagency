package com.byhovsky.agency.exception;

/**
 * Repository exception.
 */
public class RepositoryException extends RuntimeException {


    /**
     * Instantiates a new Repository exception.
     */
    public RepositoryException() {
        super();
    }

    /**
     * Instantiates a new Repository exception.
     *
     * @param message the message
     */
    public RepositoryException(String message) {
        super(message);
    }

    /**
     * Instantiates a new Repository exception.
     *
     * @param message the message
     * @param cause   the cause
     */
    public RepositoryException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Instantiates a new Repository exception.
     *
     * @param cause the cause
     */
    public RepositoryException(Throwable cause) {
        super(cause);
    }

    /**
     * Instantiates a new Repository exception.
     *
     * @param message            the message
     * @param cause              the cause
     * @param enableSuppression  the enable suppression
     * @param writableStackTrace the writable stack trace
     */
    protected RepositoryException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }


}
