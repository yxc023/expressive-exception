package com.yangxiaochen.exception.spring.impl;

import com.yangxiaochen.exception.spring.ExceptionTranslator;
import org.springframework.http.converter.HttpMessageConversionException;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.HttpMediaTypeException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.multipart.MultipartException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

/**
 * @author yangxiaochen
 */
public class SpringMvcExceptionTranslator implements ExceptionTranslator {
    @Override
    public Exception translate(Exception e, HttpServletRequest request) {

        if (e instanceof MethodArgumentTypeMismatchException) {
            return newWebApiException((MethodArgumentTypeMismatchException) e);
        }

        if (e instanceof MissingServletRequestParameterException) {
            String name = ((MissingServletRequestParameterException) e).getParameterName();
            return new WebApiException("缺少参数: " + name, e);
        }

        if (e instanceof HttpMediaTypeException || e instanceof HttpMessageConversionException) {
            return new WebApiException("请求数据格式错", e);
        }

        if (e instanceof MethodArgumentNotValidException) {
            return newWebApiException((MethodArgumentNotValidException) e);
        }

        if (e instanceof MultipartException) {
            return newWebApiException((MultipartException) e);
        }

        if (e instanceof IllegalArgumentException) {
            return new WebApiException("请求数据验证有错误", e);
        }

        if (e instanceof ServletException) {
            // 其他的 servlet 异常, 不做处理, 由 spring 处理
            return null;
        }

        return e;
    }

    private Exception newWebApiException(MethodArgumentTypeMismatchException e) {
        String name = e.getName();
        Object value = e.getValue();
        if (value == null) {
            return new WebApiException("缺少参数: " + name, e);
        } else {
            return new WebApiException("参数类型错误: " + name + ": " + value, e);
        }
    }

    private Exception newWebApiException(MethodArgumentNotValidException e) {
        StringBuilder errorMsgBuilder = new StringBuilder();
        for (FieldError error : e.getBindingResult().getFieldErrors()) {
            String field = error.getField();
            String msg = error.getDefaultMessage();
            errorMsgBuilder.append("字段错误：[" + field + "]: " + msg).append("; ");
        }
        for (ObjectError error : e.getBindingResult().getGlobalErrors()) {
            errorMsgBuilder.append("数据错误：[" + error.getObjectName() + "]: " + error.getDefaultMessage()).append("; ");
        }
        return new WebApiException("请求数据验证有错误：" + errorMsgBuilder.toString(), e);
    }

    private Exception newWebApiException(MultipartException e) {
        if (e instanceof MaxUploadSizeExceededException) {
            return new WebApiException("文件过大", e);
        } else {
            return new WebApiException("文件上传错误：", e);
        }
    }
}
