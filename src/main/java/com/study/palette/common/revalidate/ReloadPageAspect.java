package com.study.palette.common.revalidate;

import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

@Aspect
@Component
public class ReloadPageAspect {
    private final WebClient webClient;

    @Autowired
    public ReloadPageAspect(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.baseUrl("http://localhost:8080").build(); //TODO 실제 사용될 도메인으로 변경 해야함
    }

    @AfterReturning("@annotation(ReloadPage)")
    public void afterReloadPage() {
        webClient
                .get()
                .uri("/reload") // 리로드를 수행할 프론트엔드 엔드포인트 지정 TODO 실제 사용될 url로 변경 해야함
                .retrieve()
                .bodyToMono(Void.class)
                .subscribe(); // 비동기적으로 요청을 보내고 응답을 무시
    }
}
