
----

### @Repeat annotation
#### 기능   
* Test Method 를 1회 이상 반복 수행 할 수 있다.

#### 속성
   1. value (Default : 1 )
      * 반복 수행 할 횟수  최대 Integer.MAX 값을 가질 수 있다. (음수나 0은 1로 처리된다)
   1. Mode (Default Mode.INDEPENDENT)
     * INDEPENDENT : 각각의 테스트가 독립적인 Context 에서 수행 된다 (Statless)
     * CONTINUOUS : N 번의 테스트가 하나의 Context 에서 수행 된다 (Stateful)

#### 예제
```$xslt
@RunWith(RepeatRunner.class)
public class Print1To10 {
    private AtomicInteger counter = new AtomicInteger();
    @Test @Repeat(value = 10, mode = Repeat.Mode.CONTINUOUS)
    public void print() {
        System.out.print(counter.incrementAndGet());
    }
}
output> 12345678910
```

----

