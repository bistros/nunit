package com.bistros.nunit.runner.statement;

import com.bistros.nunit.annotation.Repeat;

import org.junit.Test;
import org.junit.runners.model.FrameworkMethod;

import static org.junit.Assert.assertEquals;

public class NunitRepeatStatementTest {

    @Repeat(value = 10, mode = Repeat.Mode.CONTINUOUS)
    public void mockTestContinuousMode() {
    }

    @Repeat(value = 99)
    public void mockTestIndependent() {
    }

    @Repeat(value = - 5)
    public void mockNegativeRepeatCount(){}

    @Test
    public void testContinousMode() throws NoSuchMethodException {
        FrameworkMethod frameworkMethod =
            new FrameworkMethod(this.getClass().getMethod("mockTestContinuousMode"));
        NunitRepeat repeat = new NunitRepeat(frameworkMethod, null, null);
        assertEquals(repeat.getRepeatCount(), 10);
    }

    @Test
    public void testIndipendentMode() throws NoSuchMethodException {
        FrameworkMethod frameworkMethod =
            new FrameworkMethod(this.getClass().getMethod("mockTestIndependent"));
        NunitRepeat repeat = new NunitRepeat(frameworkMethod, null, null);
        assertEquals(repeat.getRepeatCount(), 1);
    }

    @Test
    public void testNegativeCount() throws NoSuchMethodException {
        FrameworkMethod frameworkMethod =
            new FrameworkMethod(this.getClass().getMethod("mockNegativeRepeatCount"));
        NunitRepeat repeat = new NunitRepeat(frameworkMethod, null, null);
        assertEquals(repeat.getRepeatCount(), 1);
    }

}
