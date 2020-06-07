package com.bistros.nunit.runner;

import com.bistros.nunit.annotation.Repeat;

import org.junit.runner.Description;
import org.junit.runner.notification.RunNotifier;
import org.junit.runners.BlockJUnit4ClassRunner;
import org.junit.runners.model.FrameworkMethod;
import org.junit.runners.model.InitializationError;
import org.junit.runners.model.Statement;
import org.junit.runners.model.TestClass;

import java.util.Optional;

public class RepeatRunner extends BlockJUnit4ClassRunner {

    public RepeatRunner(Class<?> klass) throws InitializationError {
        super(new TestClass(klass));
    }

    /**
     * Repeat 를 지원하기 위해서 추가된 메소드입니다.
     * Repeat Annotation 의 value 를 repeat count 로 가져오고, 해당 Annotation 이 없으면 1회 수행으로 선언합니다.
     * 실제 실행 되는 로직은 BlockJUnit4ClassRunner#runChild 와 동일합니다.
     *
     * @param description Description
     * @param method FrameworkMethod
     * @param notifier RunNotifier
     * @see BlockJUnit4ClassRunner#runChild(FrameworkMethod, RunNotifier)
     */
    private void repeatableChild(Description description,
        FrameworkMethod method, RunNotifier notifier) {

        int repeat = Optional.ofNullable(method.getAnnotation(Repeat.class))
            .map(Repeat::value).orElse(1);
        for (int i = 0; i < repeat; i++) {
            Statement statement = new Statement() {
                @Override
                public void evaluate() throws Throwable {
                    methodBlock(method).evaluate();
                }
            };
            runLeaf(statement, description, notifier);
        }
    }

    /**
     * BlockJUnit4ClassRunner#runChild(FrameworkMethod, RunNotifier)를 복사했습니다.
     * Repeat 기능을 위한 {@link #repeatableChild(Description, FrameworkMethod, RunNotifier)}를 호출합니다.
     *
     * @see BlockJUnit4ClassRunner#runChild(FrameworkMethod, RunNotifier)
     */
    @Override
    protected void runChild(final FrameworkMethod method, RunNotifier notifier) {
        Description description = describeChild(method);
        if (isIgnored(method)) {
            notifier.fireTestIgnored(description);
        } else {
            repeatableChild(description, method, notifier);
        }
    }

}
