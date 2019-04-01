package com.yangxiaochen.exception.spring;

import javax.servlet.http.HttpServletRequest;

public interface MessageExtractor {

    String extract(HttpServletRequest request, Exception ex);
}
