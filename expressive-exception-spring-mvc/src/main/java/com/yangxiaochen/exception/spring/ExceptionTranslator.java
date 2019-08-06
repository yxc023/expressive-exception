package com.yangxiaochen.exception.spring;

import javax.servlet.http.HttpServletRequest;

/**
 * @author yangxiaochen
 */
public interface ExceptionTranslator {

    Exception translate(Exception e, HttpServletRequest request);
}
