package com.cb.service;

import com.cb.config.CbrConfig;
import com.cb.model.ListValuteDto;
import com.cb.model.Valute;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Service
@RequiredArgsConstructor
public class CurrencyRateService {
    private final CbrConfig cbrConfig;
    private final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    RestTemplate restTemplate = new RestTemplate();

    public Valute getValute(String valute, LocalDate date) {
        final String URL = String.format("%s?date_req=%s", cbrConfig.getUrl(), FORMATTER.format(date));

        ListValuteDto valutes = restTemplate.getForObject(URL, ListValuteDto.class);
        Valute result = valutes.valutes.stream().filter(val -> val.getCharCode().equals(valute)).findFirst().get();

        return result;
    }
}
