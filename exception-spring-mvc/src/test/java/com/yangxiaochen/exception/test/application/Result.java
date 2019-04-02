package com.yangxiaochen.exception.test.application;


import lombok.Data;

import java.util.List;

@Data
public class Result<T> {
    private String code = "SUCCESS";
    private String msg = "成功";
    private String tip;
    private List<String> stackTrace;
    private T data;
}
