package com.cb.service;

import com.cb.config.CbrConfig;
import com.cb.model.Valute;
import com.cb.parser.ValutesParser;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CurrencyRateService {
    private final CbrConfig cbrConfig;
    private CbrRequesterImpl cbrRequester = new CbrRequesterImpl();
    private ValutesParser parser = new ValutesParser();
    private final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public Valute getValute(String valute, LocalDate date) {
        String urlWithParams = String.format("%s?date_req=%s", cbrConfig.getUrl(), FORMATTER.format(date));
        String valutesXml = cbrRequester.getValutesXml(urlWithParams);
        List<Valute> valutes = parser.parse(valutesXml);
        Valute result = valutes.stream()
                .filter(val -> val.getCharCode().equals(valute))
                .findFirst()
                .get();

        return result;
    }
}
