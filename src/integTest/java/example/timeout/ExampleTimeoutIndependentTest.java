package example.timeout;

import com.bistros.nunit.annotation.Repeat;

import com.bistros.nunit.runner.RepeatRunner;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(RepeatRunner.class)
public class ExampleTimeoutIndependentTest {

    @Test(timeout = 3000)
    @Repeat(value = 4, mode = Repeat.Mode.INDEPENDENT)
    public void testDecreases3TimesIndependentMode() throws InterruptedException {
        Thread.sleep(1000);
        Assert.assertTrue(true);
    }
    @Before
    public void testForKeepClass() {
    }

}
