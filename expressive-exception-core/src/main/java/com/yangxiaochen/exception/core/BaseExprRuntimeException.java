package com.yangxiaochen.exception.core;

import com.yangxiaochen.exception.core.level.ExceptionLevels;

/**
 * @author yangxiaochen
 */
public abstract class BaseExprRuntimeException extends RuntimeException implements HasTip, HasCode, HasData , HasLevel{
    private String tip;
    private String code;
    private Object data;
    private ExceptionLevel level;

    public BaseExprRuntimeException() {
        super();
    }

    public BaseExprRuntimeException(String message) {
        super(message);
    }

    public BaseExprRuntimeException(String message, Throwable cause) {
        super(message, cause);
    }

    public BaseExprRuntimeException(Throwable cause) {
        super(cause);
    }

    public BaseExprRuntimeException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public BaseExprRuntimeException tip(String tip) {
        this.tip = tip;
        return this;
    }

    public BaseExprRuntimeException code(String code) {
        this.code = code;
        return this;
    }

    public BaseExprRuntimeException data(Object data) {
        this.data = data;
        return this;
    }

    public BaseExprRuntimeException level(ExceptionLevel level) {
        this.level = level;
        return this;
    }

    public BaseExprRuntimeException errorLevel() {
        this.level = ExceptionLevels.ERROR;
        return this;
    }

    public BaseExprRuntimeException serviceLevel() {
        this.level = ExceptionLevels.SERVICE;
        return this;
    }

    @Override
    public Object getData() {
        return data;
    }

    @Override
    public String getTip() {
        return tip;
    }

    @Override
    public String getCode() {
        return code;
    }

    @Override
    public ExceptionLevel getLevel() {
        return level;
    }
}
