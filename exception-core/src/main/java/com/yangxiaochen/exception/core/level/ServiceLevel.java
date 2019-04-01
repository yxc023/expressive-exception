package com.yangxiaochen.exception.core.level;

import com.yangxiaochen.exception.core.ExceptionLevel;

public class ServiceLevel implements ExceptionLevel {

    public static final ServiceLevel INSTANCE = new ServiceLevel();

    private ServiceLevel() {}

    @Override
    public String getValue() {
        return "SERVICE";
    }
}
