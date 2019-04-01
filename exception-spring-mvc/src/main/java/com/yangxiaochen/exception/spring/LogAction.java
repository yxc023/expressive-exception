package com.yangxiaochen.exception.spring;

import javax.servlet.http.HttpServletRequest;

public interface LogAction {

    void log(HttpServletRequest request, Exception ex);
}
