package com.bistros.nunit.runner;

import com.bistros.nunit.annotation.Repeat;
import org.junit.runner.notification.RunNotifier;
import org.junit.runners.BlockJUnit4ClassRunner;
import org.junit.runners.model.FrameworkMethod;
import org.junit.runners.model.InitializationError;
import org.junit.runners.model.TestClass;

import java.util.Optional;

public class RepeatRunner extends BlockJUnit4ClassRunner {

    public RepeatRunner(Class<?> klass) throws InitializationError {
        super(new TestClass(klass));
    }

    @Override
    protected void runChild(final FrameworkMethod method, RunNotifier notifier) {
        repeatable(method, notifier);
    }

    private void repeatable(FrameworkMethod method, RunNotifier notifier) {
        int repeat = Optional.ofNullable(method.getAnnotation(Repeat.class))
                .map(Repeat::value).orElse(1);
        for (int i = 0; i < repeat; i++) {
            super.runChild(method, notifier);
        }
    }

}
