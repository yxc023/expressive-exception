package com.yangxiaochen.exception.spring;

import com.yangxiaochen.exception.spring.impl.DefaultLogAction;
import com.yangxiaochen.exception.spring.impl.JsonResultErrorViewResolver;
import com.yangxiaochen.exception.spring.impl.SpringMvcExceptionTranslator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.Ordered;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

/**
 * @author yangxiaochen
 */
@SuppressWarnings("ALL")
@Slf4j
public class ExceptionHandler implements HandlerExceptionResolver, Ordered {

    private ErrorViewResolver errorViewResolver = new JsonResultErrorViewResolver();
    private LogAction logAction = new DefaultLogAction();
    private HandleChecker handleChecker = request -> true;
    private List<ExceptionTranslator> exceptionTranslators;
    private List<String> pathPrefixs;


    public ExceptionHandler() {
        List<ExceptionTranslator> exceptionTranslators = new ArrayList<>();
        exceptionTranslators.add(new SpringMvcExceptionTranslator());

        List<String> pathPrefixs = new ArrayList<>();
        pathPrefixs.add("/");

        this.exceptionTranslators = exceptionTranslators;
        this.pathPrefixs = pathPrefixs;
    }

    @Override
    public int getOrder() {
        return Ordered.HIGHEST_PRECEDENCE;
    }

    @Override
    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        if (pathPrefixs.stream().noneMatch(prefix -> request.getRequestURI().startsWith(prefix))) {
            return null;
        }

        ex = translateException(ex, request);
        if (ex == null) {
            return null;
        }
        logAction.log(request, ex);

        return errorViewResolver.resolve(request, response, ex);
    }


    protected Exception translateException(Exception e, HttpServletRequest request) {
        for (ExceptionTranslator exceptionTranslator : exceptionTranslators) {
            Exception translatedException = exceptionTranslator.translate(e, request);
            if (translatedException != null) {
                return translatedException;
            }
        }
        return null;
    }


    public ErrorViewResolver getErrorViewResolver() {
        return errorViewResolver;
    }

    public void setErrorViewResolver(ErrorViewResolver errorViewResolver) {
        this.errorViewResolver = errorViewResolver;
    }

    public LogAction getLogAction() {
        return logAction;
    }

    public void setLogAction(LogAction logAction) {
        this.logAction = logAction;
    }

    public HandleChecker getHandleChecker() {
        return handleChecker;
    }

    public void setHandleChecker(HandleChecker handleChecker) {
        this.handleChecker = handleChecker;
    }

    public List<ExceptionTranslator> getExceptionTranslators() {
        return exceptionTranslators;
    }

    public void setExceptionTranslators(List<ExceptionTranslator> exceptionTranslators) {
        this.exceptionTranslators = exceptionTranslators;
    }

    public List<String> getPathPrefixs() {
        return pathPrefixs;
    }

    public void setPathPrefixs(List<String> pathPrefixs) {
        this.pathPrefixs = pathPrefixs;
    }
}