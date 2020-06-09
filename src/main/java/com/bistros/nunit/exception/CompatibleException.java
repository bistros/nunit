package com.bistros.nunit.exception;

/**
 * NUnit은 JUnit의 버전에 큰 의존성을 가지고 있습니다.
 * Reflection 등을 통해서 NUnit이 정상적으로 수행되지 않을 경우 발생합니다.
 */
public class CompatibleException extends NUnitException {
    public CompatibleException(String message, Throwable cause) {
        super(message, cause);
    }
}
