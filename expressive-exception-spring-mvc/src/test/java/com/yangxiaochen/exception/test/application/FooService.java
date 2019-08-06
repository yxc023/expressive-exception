package com.yangxiaochen.exception.test.application;

import com.yangxiaochen.exception.test.application.exception.ServiceRuntimeException;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
public class FooService {

    public void withDefaultRichException() {
        throw new ServiceRuntimeException("default service exception")
                .code("SERVICE_EXCEPTION")
                .tip("默认业务异常")
                .data(new HashMap<String, String>(){{put("key", "value");}});
    }
}
