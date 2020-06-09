package com.bistros.nunit.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.METHOD, ElementType.ANNOTATION_TYPE })
public @interface Repeat {

    /**
     * 반복 실행할 횟수를 의미합니다. 음수 or 0 이면 기본 1회 실행됩니다.
     */
    int value() default 1;

    /**
     * Repeat 대상의 메소드를 어떻게 수행할 것인가를 결정 합니다
     *
     * {@link Mode#INDEPENDENT}
     * {@link Mode#CONTINUOUS}
     */
    Mode mode() default Mode.INDEPENDENT;

    enum Mode {
        /**
         * 테스트 메소드를 각 각 독립적으로 수행합니다. Context를 개별적으로 가집니다.
         */
        INDEPENDENT,

        /**
         * 테스트 메소드를 하나의 Context 안에서 연속적으로 수행 합니다.
         */
        CONTINUOUS
    }
}
