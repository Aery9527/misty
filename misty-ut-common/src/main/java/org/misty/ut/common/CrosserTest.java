package org.misty.ut.common;

import org.mockito.Mockito;
import org.mockito.stubbing.Answer;

import java.util.concurrent.atomic.AtomicReference;

public class CrosserTest {

    AtomicReference<ClassLoader> executeClassLoader = new AtomicReference<>();

    @SuppressWarnings("rawtypes")
    public <T> T mock(Answer answer, T mock) {
        return Mockito.doAnswer((invocationOnMock) -> {
            executeClassLoader.set(Thread.currentThread().getContextClassLoader());
            return answer.answer(invocationOnMock);
        }).when(mock);
    }

    public ClassLoader getExecuteClassLoader() {
        return executeClassLoader.get();
    }

}
