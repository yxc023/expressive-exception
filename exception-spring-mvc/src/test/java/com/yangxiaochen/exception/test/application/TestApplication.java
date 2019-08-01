package com.yangxiaochen.exception.test.application;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;

@SpringBootApplication
public class TestApplication {

    @GetMapping("/getFoo")
    public Result getFooData() {
        return new Result();
    }
}
