package com.yangxiaochen.exception.spring.impl;

import com.yangxiaochen.exception.core.BaseExprRuntimeException;

/**
 * @author yangxiaochen
 */
public class WebApiException extends BaseExprRuntimeException {
    public WebApiException() {
    }

    public WebApiException(String message) {
        super(message);
    }

    public WebApiException(String message, Throwable cause) {
        super(message, cause);
    }

    public WebApiException(Throwable cause) {
        super(cause);
    }

    public WebApiException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
