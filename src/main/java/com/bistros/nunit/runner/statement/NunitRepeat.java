package com.bistros.nunit.runner.statement;

import com.bistros.nunit.annotation.Repeat;

import org.junit.runners.model.FrameworkMethod;
import org.junit.runners.model.Statement;

public class NunitRepeat extends Statement {

    private final int repeat;
    private final Statement statement;
    private final FrameworkMethod method;
    private final Object target;

    public NunitRepeat(FrameworkMethod method, Object target, Statement statement) {
        this.statement = statement;
        this.method = method;
        this.target = target;
        this.repeat = getRepeatCount();
    }

    /**
     * repeat 는 1부터 Integer.Max 까지 값을 가질 수 있습니다.
     * <p/>
     * 단 다음의 경우는 '1' 로 정의되고 테스트가 1회 수행됩니다.
     * case Repeat Annotation 이 없다 : 1
     * case Repeat 가 있지만, Mode 가 INDEPENDENT 이다 : 1 (즉, 테스트 마다 각각 Report 됨)
     * case Repeat value가 1보다 작다 : 1
     *
     * @return 메소드를 테스트할 횟수를 반환합니다.
     */
    protected final int getRepeatCount() {
        Repeat repeatAnno = method.getAnnotation(Repeat.class);
        if (repeatAnno != null && Repeat.Mode.CONTINUOUS == repeatAnno.mode()) {
            return Math.max(1, repeatAnno.value());
        }
        return 1;
    }

    @Override
    public void evaluate() throws Throwable {
        for (int i = 0; i < repeat; i++) {
            this.statement.evaluate();
        }
    }
}
