package com.yangxiaochen.exception.spring.impl;

import com.yangxiaochen.exception.core.HasCode;
import com.yangxiaochen.exception.core.HasData;
import com.yangxiaochen.exception.core.HasTip;
import com.yangxiaochen.exception.spring.ErrorViewResolver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author yangxiaochen
 */
public class DefaultApiErrorViewResolver implements ErrorViewResolver {
    private static Logger logger = LoggerFactory.getLogger(DefaultApiErrorViewResolver.class);
    MappingJackson2JsonView view = new MappingJackson2JsonView();

    @Override
    public ModelAndView resolve(HttpServletRequest request, HttpServletResponse response, Exception ex) {
        ModelAndView mv = new ModelAndView();
        mv.addObject("msg", ex.getMessage());
        if (ex instanceof HasCode) {
            mv.addObject("code", ((HasCode) ex).getCode());
        }
        if (ex instanceof HasTip) {
            mv.addObject("tip", ((HasTip) ex).getTip());
        }
        if (ex instanceof HasData) {
            mv.addObject("data", ((HasData) ex).getData());
        }
        mv.setView(view);
        return mv;
    }
}
