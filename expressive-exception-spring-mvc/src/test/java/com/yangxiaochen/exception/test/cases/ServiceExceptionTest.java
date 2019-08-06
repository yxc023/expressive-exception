package com.yangxiaochen.exception.test.cases;

import com.yangxiaochen.exception.test.AbstractTestCase;
import com.yangxiaochen.exception.test.application.Result;
import org.assertj.core.api.Assertions;
import org.junit.Test;

public class ServiceExceptionTest extends AbstractTestCase {


    @Test
    public void exampleTest() {
        Result result = this.webClient.get().uri("/getFoo").header("Accept", "application/json")
                .exchange()
                .expectStatus().isOk()
                .expectBody(Result.class).returnResult().getResponseBody();

        Assertions.assertThat(result.getCode()).isEqualTo("SERVICE_EXCEPTION");
        Assertions.assertThat(result.getTip()).isEqualTo("默认业务异常");
    }


    @Test
    public void test() {

    }
}
