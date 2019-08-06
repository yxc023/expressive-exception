package com.yangxiaochen.exception.spring.impl;

import com.yangxiaochen.exception.core.HasCode;
import com.yangxiaochen.exception.core.HasData;
import com.yangxiaochen.exception.core.HasTip;
import com.yangxiaochen.exception.spring.ErrorViewResolver;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * @author yangxiaochen
 */
public class JsonResultErrorViewResolver implements ErrorViewResolver {

    private boolean printStack = false;
    private MappingJackson2JsonView view = new MappingJackson2JsonView();

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
        if (printStack) {
            mv.addObject("stackTrace", getStackFrames(ex));
        }
        mv.setView(view);
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
        return list.toArray(new String[0]);
    }


    public boolean isPrintStack() {
        return printStack;
    }

    public void setPrintStack(boolean printStack) {
        this.printStack = printStack;
    }
}
