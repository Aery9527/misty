package org.misty.smooth.core.error;

import org.misty.smooth.core.error.adpater.SmoothManagerLoadExceptionThrownAdapter;
import org.misty.smooth.manager.error.*;
import org.misty.util.error.MistyErrorDefinition;

import java.util.function.BiFunction;
import java.util.function.Function;

public enum SmoothDomainLoadError implements MistyErrorDefinition<SmoothLoadException> {
    UNEXPECTED("10", SmoothLoadException::new, SmoothLoadException::new),
    CLASSLOADER_BUILD_ERROR("11", SmoothLoadClassLoaderException::new, SmoothLoadClassLoaderException::new),
    LOAD_STATE_WRONG("12", SmoothLoadStateWrongException::new, SmoothLoadStateWrongException::new),
    LOAD_TYPE_DUPLICATE("13", SmoothLoadTypeDuplicateException::new, SmoothLoadTypeDuplicateException::new),
    LOAD_FINISH_ACTION_REGISTER("14", SmoothLoadFinishRegisterException::new, SmoothLoadFinishRegisterException::new),
    ;

    private final String type = getClass().getSimpleName();

    private final String code;

    private final SmoothManagerLoadExceptionThrownAdapter thrownAdapter;

    SmoothDomainLoadError(String code, Function<String, SmoothLoadException> thrown1, BiFunction<String, Throwable, SmoothLoadException> thrown2) {
        this.code = code;
        this.thrownAdapter = new SmoothManagerLoadExceptionThrownAdapter(thrown1, thrown2);
    }

    @Override
    public String getType() {
        return this.type;
    }

    @Override
    public String getCode() {
        return this.code;
    }

    @Override
    public SmoothLoadException thrown() throws SmoothLoadException {
        throw this.thrownAdapter.thrown(this);
    }

    @Override
    public SmoothLoadException thrown(String msg) throws SmoothLoadException {
        throw this.thrownAdapter.thrown(this, msg);
    }

    @Override
    public SmoothLoadException thrown(Throwable cause) throws SmoothLoadException {
        throw this.thrownAdapter.thrown(this, cause);
    }

    @Override
    public SmoothLoadException thrown(String msg, Throwable cause) throws SmoothLoadException {
        throw this.thrownAdapter.thrown(this, msg, cause);
    }
}
