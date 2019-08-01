package com.yangxiaochen.exception.core;

/**
 * @author yangxiaochen
 */
public abstract class BaseRichRuntimeException extends RuntimeException implements HasTip, HasCode, HasData {
    private String tip;
    private String code;
    private Object data;

    public BaseRichRuntimeException() {
        super();
    }

    public BaseRichRuntimeException(String message) {
        super(message);
    }

    public BaseRichRuntimeException(String message, Throwable cause) {
        super(message, cause);
    }

    public BaseRichRuntimeException(Throwable cause) {
        super(cause);
    }

    public BaseRichRuntimeException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public BaseRichRuntimeException tip(String tip) {
        this.tip = tip;
        return this;
    }

    public BaseRichRuntimeException code(String code) {
        this.code = code;
        return this;
    }

    public BaseRichRuntimeException data(Object data) {
        this.data = data;
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

}
