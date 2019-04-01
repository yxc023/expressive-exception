package com.yangxiaochen.exception.spring;

import javax.servlet.http.HttpServletRequest;

/**
 * @author yangxiaochen
 */
public interface ApiRequestChecker {
    boolean isApiRequest(HttpServletRequest request);
}
