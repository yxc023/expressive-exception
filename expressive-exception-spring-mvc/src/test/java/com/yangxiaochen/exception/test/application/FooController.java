package com.yangxiaochen.exception.test.application;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FooController {

    private FooService fooService;
    public FooController(FooService fooService) {
        this.fooService = fooService;
    }


    @GetMapping("/getFoo")
    public Result getFoo() {
        fooService.withDefaultRichException();
        return null;
    }
}
