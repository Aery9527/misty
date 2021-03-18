package org.misty.smooth.api.service;

import org.misty.smooth.api.vo.SmoothRequest;
import org.misty.smooth.api.vo.SmoothResponse;

public interface SmoothService {

    SmoothResponse serve(SmoothRequest request);

}
