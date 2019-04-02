package com.yangxiaochen.exception.spring;

import javax.servlet.http.HttpServletRequest;

/**
 * @author yangxiaochen
 */
public interface HandleChecker {
    boolean shouldHandle(HttpServletRequest request);
}
