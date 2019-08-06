package com.yangxiaochen.exception.spring;

import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author yangxiaochen
 */
public interface ErrorViewResolver {
    ModelAndView resolve(HttpServletRequest request, HttpServletResponse response, Exception ex);
}
