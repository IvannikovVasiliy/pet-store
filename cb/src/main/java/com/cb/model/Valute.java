package com.cb.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Valute {
    private String numCode;
    private String charCode;
    private String nominal;
    private String name;
    private String value;
}
