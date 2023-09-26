package com.study.palette.common.revalidate;

import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Log4j2
public class ReloadTestController {
    @GetMapping("test/aop")
    @ReloadPage // 해당 어노테이션 선언시 메서드가 실행된 후 리로드요청을 보낸다.
    public String aopTest() {
        return "aop Test 리로드 실행전";
    }

    @GetMapping("/reload")
    public String reloadTest() {
        log.info("리로드 실행완료!!!!!!!!!!!!!!!!!!!!");
        return "aop Test 리로드 실행완료!!!!!!!!!!!!!!!!!!!!";
    }
}
