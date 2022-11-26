package com.pet.service;

import com.pet.client.ValuteClient;
import com.pet.pojo.Valute;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class ValuteService {
    final ValuteClient valuteClient;

    public Mono<String> getValute(String valute, LocalDate date) {
        return valuteClient.getValute(valute, date);
    }
}
