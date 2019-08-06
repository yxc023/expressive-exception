package com.yangxiaochen.exception.core;

import com.yangxiaochen.exception.core.level.ExceptionLevels;

/**
 * @author yangxiaochen
 */
public abstract class BaseExprException extends Exception implements HasTip, HasCode, HasData, HasLevel {
    private String tip;
    private String code;
    private Object data;
    private ExceptionLevel level;

    public BaseExprException() {
        super();
    }

    public BaseExprException(String message) {
        super(message);
    }

    public BaseExprException(String message, Throwable cause) {
        super(message, cause);
    }

    public BaseExprException(Throwable cause) {
        super(cause);
    }

    public BaseExprException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public BaseExprException tip(String tip) {
        this.tip = tip;
        return this;
    }

    public BaseExprException code(String code) {
        this.code = code;
        return this;
    }

    public BaseExprException data(Object data) {
        this.data = data;
        return this;
    }

    public BaseExprException level(ExceptionLevel level) {
        this.level = level;
        return this;
    }

    public BaseExprException errorLevel() {
        this.level = ExceptionLevels.ERROR;
        return this;
    }

    public BaseExprException serviceLevel() {
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
