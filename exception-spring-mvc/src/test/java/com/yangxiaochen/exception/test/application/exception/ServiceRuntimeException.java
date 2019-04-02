package com.yangxiaochen.exception.test.application.exception;

import com.yangxiaochen.exception.core.BaseRichRuntimeException;
import com.yangxiaochen.exception.core.ExceptionLevel;
import com.yangxiaochen.exception.core.HasLevel;
import com.yangxiaochen.exception.core.level.ServiceLevel;

public class ServiceRuntimeException extends BaseRichRuntimeException implements HasLevel {

    public ServiceRuntimeException() {
    }

    public ServiceRuntimeException(String message) {
        super(message);
    }

    public ServiceRuntimeException(String message, Throwable cause) {
        super(message, cause);
    }

    public ServiceRuntimeException(Throwable cause) {
        super(cause);
    }

    public ServiceRuntimeException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    @Override
    public ExceptionLevel getLevel() {
        return ServiceLevel.INSTANCE;
    }
}
