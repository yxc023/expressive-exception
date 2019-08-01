package com.yangxiaochen.exception.dubbo;

import com.alibaba.dubbo.common.Constants;
import com.alibaba.dubbo.common.extension.Activate;
import com.alibaba.dubbo.rpc.*;
import com.google.gson.Gson;
import com.yangxiaochen.exception.core.HasCode;
import com.yangxiaochen.exception.core.HasLevel;
import com.yangxiaochen.exception.core.HasTip;
import com.yangxiaochen.exception.core.level.ExceptionLevels;
import org.apache.dubbo.rpc.AppResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.ValidationException;
import java.util.Set;

@Activate(group = Constants.PROVIDER)
public class GlobalExceptionFilter implements Filter {
    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionFilter.class);

    public static final String PARAMETER_INVALID = "PARAMETER_INVALID";
    public static final String SYSTEM_ERROR = "SYSTEM_ERROR";
    private Gson gson = new Gson();
    @Override
    public Result invoke(Invoker<?> invoker, Invocation invocation) throws ApiException {
        Result result = invoker.invoke(invocation);
        if (result instanceof AppResponse && result.hasException()) {
            Throwable exception = result.getException();
            Throwable transformedException;
            if (exception instanceof ValidationException) {
                if (exception instanceof ConstraintViolationException) {
                    Set<ConstraintViolation<?>> constraintViolations = ((ConstraintViolationException) exception).getConstraintViolations();
                    StringBuilder sb = new StringBuilder();
                    for (ConstraintViolation constraintViolation : constraintViolations) {
                        sb.append(constraintViolation.getMessage()).append("|");
                    }
                    String message = sb.toString();
                    logWarn(invoker, invocation, exception);
                    transformedException = new ApiException(exception.getMessage()).code(PARAMETER_INVALID).tip("参数不合法: " + message);
                    transformedException.setStackTrace(exception.getStackTrace());
                } else {
                    // 不应走到这个分支
                    logError(invoker, invocation, exception);
                    transformedException = new ApiException(exception.getMessage())
                            .code(PARAMETER_INVALID)
                            .tip("参数校验失败");
                    transformedException.setStackTrace(exception.getStackTrace());
                }
                result.setException(transformedException);
                return result;
            }

            if (exception instanceof HasLevel) {
                if (((HasLevel) exception).getLevel() == ExceptionLevels.ERROR) {
                    logError(invoker, invocation, exception);
                } else {
                    logWarn(invoker, invocation, exception);
                }
            } else {
                logError(invoker, invocation, exception);
            }

            String code;
            if (exception instanceof HasCode) {
                code = ((HasCode) exception).getCode();
            } else {
                code = SYSTEM_ERROR;
            }

            String tip;
            if (exception instanceof HasTip) {
                tip = ((HasTip) exception).getTip();
            } else {
                tip = "服务器内部错误";
            }


            transformedException = new ApiException(exception.getMessage()).code(code).tip(tip);
            transformedException.setStackTrace(exception.getStackTrace());
            result.setException(transformedException);
            return result;
        }
        return result;
    }

    private void logWarn(Invoker<?> invoker, Invocation invocation, Throwable exception) {
        logger.warn(String.format("dubbo invoke exception: %s, from: %s, interface: %s, method: %s, params: %s",
                exception.getMessage(), RpcContext.getContext().getRemoteHost(), invoker.getInterface().getName(), invocation.getMethodName(), gson.toJson(invocation.getArguments())
        ), exception);
    }

    private void logError(Invoker<?> invoker, Invocation invocation, Throwable exception) {
        logger.error(String.format("dubbo invoke exception: %s, from: %s, interface: %s, method: %s, params: %s",
                exception.getMessage(), RpcContext.getContext().getRemoteHost(), invoker.getInterface().getName(), invocation.getMethodName(), gson.toJson(invocation.getArguments())
        ), exception);
    }


}
