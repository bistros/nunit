package com.bistros.nunit.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
public @interface Repeat {

    /**
     * 반복 실행할 횟수를 의미합니다. 음수 or 0 이면 기본 1회 실행됩니다.
     */
    int value() default 1;
}
