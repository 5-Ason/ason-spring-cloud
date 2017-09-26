package com.ason.core.exception;

import com.ason.result.ResponseInfo;

/**
 * Created by Ason on 2017/9/7.
 */
public class RmsException extends RuntimeException {
    private ResponseInfo responseInfo;

    public RmsException(ResponseInfo responseInfo) {
        this.responseInfo = responseInfo;
    }

    public ResponseInfo getResponseInfo() {
        return responseInfo;
    }

    public void setResponseInfo(ResponseInfo responseInfo) {
        this.responseInfo = responseInfo;
    }
}
