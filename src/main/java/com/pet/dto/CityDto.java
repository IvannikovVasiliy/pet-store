package com.pet.dto;

import lombok.Builder;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Builder
public class CityDto {
    @NotEmpty
    @Size(max = 50)
    public String name;

    @NotNull
    public CountryDto country;
}
