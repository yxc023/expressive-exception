package com.yangxiaochen.exception.spring;

import javax.servlet.http.HttpServletRequest;

/**
 * @author yangxiaochen
 */
public interface LogAction {

    void log(HttpServletRequest request, Exception ex);
}
