package com.pet.client;

import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pet.configs.ValuteClientConfig;
import com.pet.pojo.Valute;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Component
@RequiredArgsConstructor
public class ValuteClient {
    private final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("dd-MM-yyyy");
    private ValuteClientConfig urlConfig;
    private final HttpClientJdk client;
    private ObjectMapper objectMapper;

    public Mono<String> getValute(String valute, LocalDate date) {
        // Адрес броать из application.properties
        //String url = "http://localhost:8081/cb/USD?date=02-03-2015";
        String url = "http://localhost:8081/cb/USD?date=" + DATE_FORMATTER.format(date);

        return client.performRequest(url);
    }

    private Valute parse(String rateAsString) {
        try {
            return objectMapper.readValue(rateAsString, Valute.class);
        } catch (Exception ex) {
            throw new RuntimeException("Can't parse string:" + rateAsString);
        }
    }
}
