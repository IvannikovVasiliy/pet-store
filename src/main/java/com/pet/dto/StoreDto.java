package com.pet.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Builder
public class StoreDto {
    @NotEmpty
    @Size(max = 50)
    public String name;

    @NotEmpty
    @Size(max = 50)
    public String cityName;

    @Override
    public String toString() {
        return String.format("{" +
                "\"name\": \"%s\", " +
                "\"cityName\": \"%s\"" +
                "}",
                name, cityName);
    }
}
