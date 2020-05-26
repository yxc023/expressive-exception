package com.yangxiaochen.exception.spring.impl;

import com.yangxiaochen.exception.core.BaseExprRuntimeException;

/**
 * @author yangxiaochen
 */
public class WebApiException extends BaseExprRuntimeException {
    public WebApiException() {
        this.serviceLevel();
    }

    public WebApiException(String message) {
        super(message);
        this.serviceLevel();
    }

    public WebApiException(String message, Throwable cause) {
        super(message, cause);
        this.serviceLevel();
    }

    public WebApiException(Throwable cause) {
        super(cause);
        this.serviceLevel();
    }

    public WebApiException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
        this.serviceLevel();
    }
}
