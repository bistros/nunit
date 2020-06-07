package com.bistros.nunit;

import com.bistros.nunit.annotation.Repeat;
import com.bistros.nunit.runner.RepeatRunner;
import org.junit.Test;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class RepeatTests {

    @RunWith(RepeatRunner.class)
    public static class InnerRepeatTest {
        @Test
        @Repeat(3)
        public void testRun3Times() {
            assertTrue(true);
        }
    }

    @Test
    public void repeat() {
        JUnitCore runner = new JUnitCore();
        Result result = runner.run(InnerRepeatTest.class);
        assertEquals(0, result.getIgnoreCount());
        assertEquals(3, result.getRunCount());
    }


    @RunWith(RepeatRunner.class)
    public static class InnerRepeatTest2 {
        @Test
        public void testRun1TimeNoWithRepeat() {
            assertTrue(true);
        }
    }

    @Test
    public void repeat2() {
        JUnitCore runner = new JUnitCore();
        Result result = runner.run(InnerRepeatTest2.class);
        assertEquals(0, result.getIgnoreCount());
        assertEquals(1, result.getRunCount());
    }

}
