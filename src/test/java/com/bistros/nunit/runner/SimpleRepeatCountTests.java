package com.bistros.nunit.runner;

import com.bistros.nunit.annotation.Repeat;

import org.junit.Test;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class SimpleRepeatCountTests {

    @RunWith(RepeatRunner.class)
    public static class InnerRepeatCountTest {
        @Test
        @Repeat(-10)
        public void expectOne() {
            assertTrue(true);
        }
    }

    @Test
    public void expect1TimeRunCountWhenMinusValue() {
        JUnitCore runner = new JUnitCore();
        Result result = runner.run(InnerRepeatCountTest.class);
        assertEquals(1, result.getRunCount());
    }


    @RunWith(RepeatRunner.class)
    public static class InnerRepeatCountTest2 {
        @Test
        @Repeat(0)
        public void expectOne() {
            assertTrue(true);
        }
    }

    @Test
    public void expect1TimeRunCountWhenZeroValue() {
        JUnitCore runner = new JUnitCore();
        Result result = runner.run(InnerRepeatCountTest2.class);
        assertEquals(1, result.getRunCount());
    }

    @RunWith(RepeatRunner.class)
    public static class InnerRepeatCountTest3 {
        @Test
        @Repeat(10)
        public void expect() {
            assertTrue(true);
        }
    }

    @Test
    public void expect10TimesRunCount() {
        JUnitCore runner = new JUnitCore();
        Result result = runner.run(InnerRepeatCountTest3.class);
        assertEquals(10, result.getRunCount());
    }

}
