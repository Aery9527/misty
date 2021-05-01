package org.misty.smooth.core.domain.loader.api;

import org.misty.smooth.api.cross.SmoothCrossWrapper;
import org.misty.smooth.api.vo.SmoothId;
import org.misty.smooth.manager.loader.enums.SmoothLoadType;
import org.misty.smooth.manager.loader.vo.SmoothLoaderArgument;

public class SmoothDomainLoadTypeControllerCrossWrapper<SmoothIdType extends SmoothId<SmoothIdType>>
        extends SmoothCrossWrapper<SmoothDomainLoadTypeController<SmoothIdType>>
        implements SmoothDomainLoadTypeController<SmoothIdType> {

    public SmoothDomainLoadTypeControllerCrossWrapper(ClassLoader wrapClassLoader, SmoothDomainLoadTypeController<SmoothIdType> loadTypeController) {
        super(wrapClassLoader, loadTypeController);
    }

    @Override
    public SmoothLoadType prepareLoading(SmoothLoaderArgument loaderArgument, SmoothIdType smoothId) {
        return super.wrap(() -> {
            SmoothDomainLoadTypeController<SmoothIdType> raw = super.getWrappedTarget();
            return raw.prepareLoading(loaderArgument, smoothId);
        });
    }

    @Override
    public void release(SmoothIdType smoothId) {
        super.wrap(() -> {
            SmoothDomainLoadTypeController<SmoothIdType> raw = super.getWrappedTarget();
            raw.release(smoothId);
        });
    }

}
