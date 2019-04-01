package com.yangxiaochen.exception.spring.impl;

import com.yangxiaochen.exception.spring.ApiRequestChecker;

import javax.servlet.http.HttpServletRequest;

/**
 * @author yangxiaochen
 */
public class DefaultApiRequestChecker implements ApiRequestChecker {
    @Override
    public boolean isApiRequest(HttpServletRequest request) {
        return "application/json".equalsIgnoreCase(request.getHeader("Accept"))
                || "XMLHttpRequest".equalsIgnoreCase(request.getHeader("X-Requested-With"))
                || Boolean.TRUE.equals(Boolean.valueOf(request.getHeader("ajax")));
    }
}
