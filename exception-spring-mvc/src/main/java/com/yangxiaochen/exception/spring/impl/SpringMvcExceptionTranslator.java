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

            String name = ((MethodArgumentTypeMismatchException) e).getName();
            Object value = ((MethodArgumentTypeMismatchException) e).getValue();
            if (value == null) {
                e = new WebApiException("缺少参数: " + name, e);
            } else {
                e = new WebApiException("参数类型错误: " + name + ": " + value, e);
            }

        } else if (e instanceof MissingServletRequestParameterException) {

            String name = ((MissingServletRequestParameterException) e).getParameterName();
            e = new WebApiException("缺少参数: " + name, e);

        } else if (e instanceof HttpMediaTypeException || e instanceof HttpMessageConversionException) {

            e = new WebApiException("请求数据格式错误：" + e.getMessage(), e);

        } else if (e instanceof MethodArgumentNotValidException) {

            StringBuilder errorMsgBuilder = new StringBuilder();
            for (FieldError error : ((MethodArgumentNotValidException) e).getBindingResult().getFieldErrors()) {
                String field = error.getField();
                String msg = error.getDefaultMessage();
                errorMsgBuilder.append("字段错误：[" + field + "]: " + msg).append("; ");
            }
            for (ObjectError error : ((MethodArgumentNotValidException) e).getBindingResult().getGlobalErrors()) {
                errorMsgBuilder.append("数据错误：[" + error.getObjectName() + "]: " + error.getDefaultMessage()).append("; ");
            }
            e = new WebApiException("请求数据验证有错误：" + errorMsgBuilder.toString(), e);
        } else if (e instanceof MultipartException) {
            if (e instanceof MaxUploadSizeExceededException) {
                e = new WebApiException("文件过大", e);
            } else {
                e = new WebApiException("文件上传错误：", e);
            }
//        } else if (e instanceof AuthException) {
//            e = new WebApiException("权限未通过:" + e.getMessage(), e);
        } else if (e instanceof IllegalArgumentException) {
            e = new WebApiException("请求数据验证有错误：" + e.getMessage(), e);
        } else if (e instanceof ServletException) {
            // 其他的 servlet 异常, 不错处理, 由 spring 处理
            return null;
        }
        return e;
    }
}
