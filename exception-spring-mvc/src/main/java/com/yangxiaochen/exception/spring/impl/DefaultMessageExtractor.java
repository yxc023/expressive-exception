package com.yangxiaochen.exception.spring.impl;

import com.yangxiaochen.exception.spring.MessageExtractor;

import javax.servlet.http.HttpServletRequest;

public class DefaultMessageExtractor implements MessageExtractor {
    @Override
    public String extract(HttpServletRequest request, Exception ex) {
        return null;

    }
}
