package com.yangxiaochen.exception.spring;

import com.yangxiaochen.exception.spring.impl.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.Ordered;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.List;

public class ExceptionHandler implements HandlerExceptionResolver, Ordered {
    private static Logger logger = LoggerFactory.getLogger(ExceptionHandler.class);

    private ErrorViewResolver apiErrorViewResoler = new DefaultApiErrorViewResolver();
    private ErrorViewResolver pageErrorViewResoler = new DefaultPageErrorViewResolver();
    private LogAction logAction = new DefaultLogAction();
    private ApiRequestChecker apiRequestChecker = new DefaultApiRequestChecker();
    private List<ExceptionTranslator> exceptionTranslators = Arrays.asList(new SpringMvcExceptionTranslator());
    private List<String> pathPrefixs = Arrays.asList("/");


    /**
     * 最优先
     *
     * @return
     */
    @Override
    public int getOrder() {
        return Ordered.HIGHEST_PRECEDENCE;
    }

    @Override
    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        if (pathPrefixs.stream().allMatch(prefix -> !request.getRequestURI().startsWith(prefix))) {
            return null;
        }

        ex = translateException(ex, request);
        if (ex == null) {
            return null;
        }

        logAction.log(request, ex);

        ModelAndView mv;
        if (apiRequestChecker.isApiRequest(request)) {
            mv = apiErrorViewResoler.resolve(request, response, ex);
        } else {
            mv = pageErrorViewResoler.resolve(request, response, ex);
        }

        return mv;
    }


    /**
     * 装饰一些不可读但最好需要提示的
     *
     * @param e
     * @return
     */
    protected Exception translateException(Exception e, HttpServletRequest request) {
        for (ExceptionTranslator exceptionTranslator : exceptionTranslators) {
            Exception translatedException = exceptionTranslator.translate(e, request);
            if (translatedException != null) {
                return translatedException;
            }
        }
        return null;
    }


    public ErrorViewResolver getApiErrorViewResoler() {
        return apiErrorViewResoler;
    }

    public void setApiErrorViewResoler(ErrorViewResolver apiErrorViewResoler) {
        this.apiErrorViewResoler = apiErrorViewResoler;
    }

    public ErrorViewResolver getPageErrorViewResoler() {
        return pageErrorViewResoler;
    }

    public void setPageErrorViewResoler(ErrorViewResolver pageErrorViewResoler) {
        this.pageErrorViewResoler = pageErrorViewResoler;
    }

    public LogAction getLogAction() {
        return logAction;
    }

    public void setLogAction(LogAction logAction) {
        this.logAction = logAction;
    }

    public ApiRequestChecker getApiRequestChecker() {
        return apiRequestChecker;
    }

    public void setApiRequestChecker(ApiRequestChecker apiRequestChecker) {
        this.apiRequestChecker = apiRequestChecker;
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