package example;

import com.bistros.nunit.annotation.Repeat;
import com.bistros.nunit.runner.RepeatRunner;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.concurrent.CountDownLatch;

@RunWith(RepeatRunner.class)
public class ExampleContinuousModeTest {

    private final CountDownLatch counter = new CountDownLatch(10);
    public static String result = "";

    @Test
    @Repeat(value = 3, mode = Repeat.Mode.CONTINUOUS)
    public void testDecreases3TimesContinuousMode() {
        counter.countDown();
        result += counter.getCount();
    }

    @AfterClass
    public static void expectResultStringIs987() {
        Assert.assertEquals(
            "CONTINUOUS 하나의 CountDownLatch가 사용되어야합니다", "987", result);
    }
}
