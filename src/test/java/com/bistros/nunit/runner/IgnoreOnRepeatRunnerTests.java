package com.bistros.nunit.runner;

import junit.framework.JUnit4TestAdapter;
import junit.framework.TestResult;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertEquals;

public class IgnoreOnRepeatRunnerTests {

    @RunWith(RepeatRunner.class)
    public static class InnerIgnoreTests {
        @Ignore
        @Test
        public void ignored() {
        }

        @Test
        public void run() {
        }
    }

    @Test
    public void ignoreRunner() {
        JUnitCore runner = new JUnitCore();
        Result result = runner.run(InnerIgnoreTests.class);
        assertEquals(1, result.getIgnoreCount());
        assertEquals(1, result.getRunCount());
    }

    @Test
    public void compatibility() {
        TestResult result = new TestResult();
        new JUnit4TestAdapter(InnerIgnoreTests.class).run(result);
        assertEquals(1, result.runCount());
        assertEquals(0, result.failureCount());
    }

}
