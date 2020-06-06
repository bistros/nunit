package com.bistros.nunit.runner;

import org.junit.runners.BlockJUnit4ClassRunner;
import org.junit.runners.model.InitializationError;
import org.junit.runners.model.TestClass;

public class RepeatRunner extends BlockJUnit4ClassRunner {

    public RepeatRunner(Class<?> klass) throws InitializationError {
        super(new TestClass(klass));
    }
}
