package com.bistros.nunit.runner;

import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(RepeatRunner.class)
public class SimpleRepeatRunnerTest {

    @Test
    public void testDefaultAssertion() {
        assertTrue(true);
        assertEquals("Hello " + "World", "Hello World");
    }

}