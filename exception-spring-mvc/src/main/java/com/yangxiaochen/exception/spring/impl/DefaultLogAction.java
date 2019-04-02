package com.yangxiaochen.exception.spring.impl;

import com.yangxiaochen.exception.core.HasLevel;
import com.yangxiaochen.exception.core.level.ErrorLevel;
import com.yangxiaochen.exception.core.level.ServiceLevel;
import com.yangxiaochen.exception.spring.LogAction;
import org.apache.catalina.connector.ClientAbortException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class DefaultLogAction implements LogAction {

    private static Logger logger = LoggerFactory.getLogger(DefaultLogAction.class);

    @Override
    public void log(HttpServletRequest request, Exception ex) {

        if (ex instanceof HasLevel && ((HasLevel) ex).getLevel() == ServiceLevel.INSTANCE) {

            logger.warn(ex.getLocalizedMessage(), ex);

        } else if (ex instanceof HasLevel && ((HasLevel) ex).getLevel() == ErrorLevel.INSTANCE) {


            logger.error(ex.getLocalizedMessage(), ex);

        } else {

            if (ex instanceof IOException &&
                    (ex.getMessage().equalsIgnoreCase("Broken pipe")
                            || ex.getMessage().equalsIgnoreCase("Connection reset by peer")
                    )) {
                logger.warn(ex.getLocalizedMessage(), ex);
            } else if (ex instanceof ClientAbortException) {
                logger.warn(ex.getLocalizedMessage(), ex);
            } else {
                logger.error(ex.getLocalizedMessage(), ex);
            }
        }
    }


}
