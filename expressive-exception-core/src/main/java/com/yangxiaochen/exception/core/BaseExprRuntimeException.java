package com.yangxiaochen.exception.core;

import com.yangxiaochen.exception.core.level.ExceptionLevels;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author yangxiaochen
 */
public abstract class BaseExprRuntimeException extends RuntimeException implements HasTip, HasCode, HasData, HasLevel {
    private String tip;
    private String code;
    private Object data;
    private ExceptionLevel level;
    private Map<String, String> ctxVars;


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

    @Override
    public String getMessage() {
        if (ctxVars != null && ctxVars.size() > 0) {
            StringBuilder s = new StringBuilder();
            s.append(super.getMessage()).append(" - ");
            int size = ctxVars.entrySet().size();
            int appendCount = 0;
            for (Map.Entry<String, String> entry : ctxVars.entrySet()) {
                s.append(entry.getKey()).append("=").append(entry.getValue());
                if (++appendCount < size) {
                    s.append(", ");
                }
            }
            return s.toString();
        }
        return super.getMessage();
    }

    /**
     * set context variable to exception, then these variables will append to exception message.
     *
     * @param key
     * @param value
     * @return
     */
    public BaseExprRuntimeException var(String key, Object value) {
        if (ctxVars == null) {
            ctxVars = new LinkedHashMap<>();
        }
        ctxVars.putIfAbsent(key, value == null ? "null" : value.toString());
        return this;
    }
}
