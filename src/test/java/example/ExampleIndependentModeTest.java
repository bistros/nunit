package example;

import com.bistros.nunit.annotation.Repeat;
import com.bistros.nunit.runner.RepeatRunner;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.concurrent.CountDownLatch;

@RunWith(RepeatRunner.class)
public class ExampleIndependentModeTest {


    private final CountDownLatch counter = new CountDownLatch(10);
    public static String result = "";

    @Test
    @Repeat(value = 3, mode = Repeat.Mode.INDEPENDENT)
    public void testDecreases3TimesIndependentMode() {
        counter.countDown();
        result += counter.getCount();
    }

    @AfterClass
    public static void expectResultStringIs999() {
        Assert.assertEquals(
            "INDEPENDENT  CountDownLatch가 매번 새로 생성 되어야 합니다", "999", result);
    }


}
