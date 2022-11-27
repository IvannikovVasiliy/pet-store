package com.cb.model;

import lombok.*;
import org.springframework.stereotype.Component;

@Component
@NoArgsConstructor
@Data
@AllArgsConstructor
@Builder
public class Valute {
    private String numCode;
    private String charCode;
    private String nominal;
    private String name;
    private String value;
}
