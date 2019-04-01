package com.yangxiaochen.exception.spring.impl;

import com.yangxiaochen.exception.spring.ErrorViewResolver;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class DefaultPageErrorViewResolver implements ErrorViewResolver {

    private boolean printStack = false;

    @Override
    public ModelAndView resolve(HttpServletRequest request, HttpServletResponse response, Exception ex) {
        ModelAndView mv = new ModelAndView("/default-exception-error");
        mv.addObject("error", ex.getMessage());
        mv.addObject("status", HttpStatus.INTERNAL_SERVER_ERROR);
        mv.addObject("path", request.getRequestURI());
        mv.addObject("exception", ex.getClass().getName());
        mv.addObject("message", ex.getLocalizedMessage());
        if (printStack) {
            mv.addObject("trace", getStackFrames(ex));
        }
        response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
        return mv;
    }

    private String[] getStackFrames(Throwable e) {
        final StringWriter sw = new StringWriter();
        final PrintWriter pw = new PrintWriter(sw, true);
        e.printStackTrace(pw);
        String stackTrace = sw.getBuffer().toString();

        final String linebreak = System.lineSeparator();
        final StringTokenizer frames = new StringTokenizer(stackTrace, linebreak);
        final List<String> list = new ArrayList<>();
        while (frames.hasMoreTokens()) {
            list.add(frames.nextToken());
        }
        return list.toArray(new String[list.size()]);
    }
}
