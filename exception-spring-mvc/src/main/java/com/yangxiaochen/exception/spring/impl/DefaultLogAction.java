package com.yangxiaochen.exception.spring.impl;

import com.yangxiaochen.exception.core.HasLevel;
import com.yangxiaochen.exception.core.level.ExceptionLevels;
import com.yangxiaochen.exception.spring.LogAction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;

public class DefaultLogAction implements LogAction {

    private static Logger logger = LoggerFactory.getLogger(DefaultLogAction.class);

    @Override
    public void log(HttpServletRequest request, Exception ex) {

        if (ex instanceof HasLevel && ((HasLevel) ex).getLevel() == ExceptionLevels.SERVICE) {
            logger.warn(ex.getLocalizedMessage(), ex);
        } else if (ex instanceof HasLevel && ((HasLevel) ex).getLevel() == ExceptionLevels.ERROR) {
            logger.error(ex.getLocalizedMessage(), ex);
        } else {
            logger.error(ex.getLocalizedMessage(), ex);
        }
    }


}
