package com.globalsqa.common.exception;

/**
 * Test automation functionality regarding base exception.
 *
 * @author Aliaksei Pershyts
 */
public class BaseException extends RuntimeException {
    public BaseException(String message) {
        super(message);
    }

    public BaseException(String message, Throwable cause) {
        super(message, cause);
    }

    public BaseException(Throwable throwable) {
        super(throwable);
    }
}
