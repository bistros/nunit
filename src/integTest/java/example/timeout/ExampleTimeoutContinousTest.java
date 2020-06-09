package example.timeout;

import com.bistros.nunit.annotation.Repeat;
import com.bistros.nunit.runner.RepeatRunner;

import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(RepeatRunner.class)
public class ExampleTimeoutContinousTest {

    //Continuous 으로 실행 할때 Timeout이 발생하는 테스트. 주석을 제거하고 확인 가능하다.
    @Test(timeout = 2000)
    @Repeat(value = 3, mode = Repeat.Mode.CONTINUOUS)
    public void testDecreases3TimesContinuousMode() throws InterruptedException {
        Thread.sleep(1000);
    }

    @Test
    public void testForKeepClass() {
    }
}
