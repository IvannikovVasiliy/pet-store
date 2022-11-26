package com.pet.client;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Component
@Slf4j
@RequiredArgsConstructor
public class HttpClientJdk {
    private final WebClient.Builder webBuilder;

    public Mono<String> performRequest(String url) {
        WebClient client = webBuilder.baseUrl(url).build();

        try {
            return client.get()
                    .accept(MediaType.APPLICATION_JSON)
                    .retrieve()
                    .bodyToMono(String.class)
                    .doOnError(error -> log.error("Http request error, url:{}", url, error))
                    .doOnNext(val -> log.info("val:{}", val));
        } catch (Exception ex) {
            throw new RuntimeException(ex.getMessage());
        }
    }
}
