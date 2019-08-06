package com.yangxiaochen.exception.core.level;

import com.yangxiaochen.exception.core.ExceptionLevel;

public class ErrorLevel implements ExceptionLevel {

    public static final ErrorLevel INSTANCE = new ErrorLevel();

    private ErrorLevel() {
    }

    @Override
    public String getValue() {
        return "ERROR";
    }
}
