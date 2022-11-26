package com.pet.pojo;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Value;

@Builder
@Value
public class Valute {
    String charCode;
    String nominal;
    String value;

    @JsonCreator
    public Valute(@JsonProperty("charCode") String charCode,
                        @JsonProperty("nominal") String nominal,
                        @JsonProperty("value") String value) {
        this.charCode = charCode;
        this.nominal = nominal;
        this.value = value;
    }
}
