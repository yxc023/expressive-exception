package com.yangxiaochen.exception.core;

/**
 * @author yangxiaochen
 */
public abstract class BaseRichException extends Exception implements HasTip, HasCode, HasData {
    private String tip;
    private String code;
    private Object data;

    public BaseRichException() {
        super();
    }

    public BaseRichException(String message) {
        super(message);
    }

    public BaseRichException(String message, Throwable cause) {
        super(message, cause);
    }

    public BaseRichException(Throwable cause) {
        super(cause);
    }

    public BaseRichException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public BaseRichException tip(String tip) {
        this.tip = tip;
        return this;
    }

    public BaseRichException code(String code) {
        this.code = code;
        return this;
    }

    public BaseRichException data(Object data) {
        this.data = data;
        return this;
    }

    @Override
    public Object getData() {
        return data;
    }

    @Override
    public String getTip() {
        if (tip == null) {
            return getMessage();
        }
        return tip;
    }

    @Override
    public String getCode() {
        return code;
    }
}
