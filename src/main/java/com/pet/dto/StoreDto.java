package com.pet.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Builder
public class StoreDto {
    @NotEmpty
    @Size(max = 50)
    public String name;

    @NotEmpty
    @Size(max = 50)
    public String cityName;

    @NotNull
    public CityDto city;

    @Override
    public String toString() {
        return String.format("{" +
                "\"name\": \"%s\", " +
                "\"cityName\": \"%s\"" +
                "}",
                name, cityName);
    }
}
