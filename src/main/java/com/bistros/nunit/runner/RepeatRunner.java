package com.bistros.nunit.runner;

import com.bistros.nunit.annotation.Repeat;

import com.bistros.nunit.exception.CompatibleException;

import com.bistros.nunit.runner.statement.NunitRepeat;

import org.junit.internal.runners.model.ReflectiveCallable;
import org.junit.internal.runners.statements.Fail;
import org.junit.runner.Description;
import org.junit.runner.notification.RunNotifier;
import org.junit.runners.BlockJUnit4ClassRunner;
import org.junit.runners.model.FrameworkMethod;
import org.junit.runners.model.InitializationError;
import org.junit.runners.model.Statement;
import org.junit.runners.model.TestClass;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class RepeatRunner extends BlockJUnit4ClassRunner {

    public RepeatRunner(Class<?> klass) throws InitializationError {
        super(new TestClass(klass));
    }

    /**
     * Repeat 를 지원하기 위해서 추가된 메소드입니다.
     * Repeat Annotation 의 value 를 repeat count 로 가져오고, 해당 Annotation 이 없으면 1회 수행으로 선언합니다.
     * 실제 실행 되는 로직은 BlockJUnit4ClassRunner#runChild 와 동일합니다.
     *
     * @see BlockJUnit4ClassRunner#runChild(FrameworkMethod, RunNotifier)     *
     */
    private void repeatableChild(Description description,
        FrameworkMethod method, RunNotifier notifier) {

        int repeat = getRepeatCount(method);

        for (int seq = 0; seq < repeat; seq++) {
            Statement statement = new Statement() {
                @Override
                public void evaluate() throws Throwable {
                    methodBlock(method).evaluate();
                }
            };
            Description desc = (repeat <= 1) ? description : createDescriptionOrder(method, seq);
            runLeaf(statement, desc, notifier);
        }
    }

    private int getRepeatCount(FrameworkMethod method) {
        Repeat repeatAnno = method.getAnnotation(Repeat.class);
        if (repeatAnno != null && Repeat.Mode.INDEPENDENT == repeatAnno.mode()) {
            return Math.max(1, repeatAnno.value());
        }
        return 1;
    }

    private Description createDescriptionOrder(FrameworkMethod method, int index) {
        String name = testName(method) + "#" + (index + 1);
        return Description.createTestDescription(
            getTestClass().getJavaClass(), name, method.getAnnotations());
    }

    /**
     * BlockJUnit4ClassRunner#runChild(FrameworkMethod, RunNotifier)를 복사했습니다.
     * Repeat 기능을 위한 {@link #repeatableChild(Description, FrameworkMethod, RunNotifier)}를 호출합니다.
     *
     * @see BlockJUnit4ClassRunner#runChild(FrameworkMethod, RunNotifier)
     */
    @Override
    protected void runChild(final FrameworkMethod method, RunNotifier notifier) {
        Description description = describeChild(method);
        if (isIgnored(method)) {
            notifier.fireTestIgnored(description);
        } else {
            repeatableChild(description, method, notifier);
        }
    }


    /**
     * Repeat Statement를 rule 수행 이후에 동작시키기 위해
     * {@link BlockJUnit4ClassRunner#methodBlock(FrameworkMethod)} 를 복사해서 약간 수정하였습니다.
     */
    @Override
    protected Statement methodBlock(final FrameworkMethod method) {
        Object test;
        try {
            test = new ReflectiveCallable() {
                @Override
                protected Object runReflectiveCall() throws Throwable {
                    return createTest(method);
                }
            }.run();
        } catch (Throwable e) {
            return new Fail(e);
        }

        Statement statement = methodInvoker(method, test);
        statement = possiblyExpectingExceptions(method, test, statement);
        statement = withRepeat(method, test, statement);    //add
        statement = withPotentialTimeout(method, test, statement);
        statement = withBefores(method, test, statement);
        statement = withAfters(method, test, statement);
        statement = withRulesCustomize(method, test, statement); //custom
        statement = withInterruptIsolation(statement);
        return statement;
    }

    private final String methodWithRules = "withRules";

    // 기존의 JUnit4에서 Rule을 처리하는 withRules 메소드는 private 이기 때문에 reflection 을 통해서 호출한다.
    protected Statement withRulesCustomize(FrameworkMethod method,
        Object target, Statement statement) {
        try {
            Method withRuleMethod = BlockJUnit4ClassRunner.class.getDeclaredMethod(
                methodWithRules, FrameworkMethod.class, Object.class, Statement.class);
            withRuleMethod.setAccessible(true);
            return (Statement) withRuleMethod.invoke(this, method, target, statement);
        } catch (InvocationTargetException | NoSuchMethodException | IllegalAccessException e) {
            throw new CompatibleException("JUnit 4.13 을 지원합니다.", e);
        }
    }

    public Statement withRepeat(FrameworkMethod method, Object target, Statement statement) {
        return new NunitRepeat(method, target, statement);

    }


}
