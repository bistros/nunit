package example;

import com.bistros.nunit.annotation.Repeat;
import com.bistros.nunit.runner.RepeatRunner;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertTrue;

@RunWith(RepeatRunner.class)
public class ExampleRepeatTests {


    @Test
    @Repeat(3)
    public void repeatTest() {
        assertTrue(true);
    }

    @Test
    @Repeat(10)
    @Ignore
    public void ignoreRepeatTest() {
        assertTrue(true);
    }
}
