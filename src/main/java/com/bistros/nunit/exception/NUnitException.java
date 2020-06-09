package com.bistros.nunit.exception;

/**
 * NUnit 의 모든 Exception의 SuperClass
 * RuntimeException 을 extends 하여서, NUnit 패키지내에서 발생한 Exception 임을 표현하기 위한 목적이다.
 */
public class NUnitException extends RuntimeException {
    public NUnitException() {
    }

    public NUnitException(String message) {
        super(message);
    }

    public NUnitException(Throwable cause) {
        super(cause);
    }

    public NUnitException(String message, Throwable cause) {
        super(message, cause);
    }
}
