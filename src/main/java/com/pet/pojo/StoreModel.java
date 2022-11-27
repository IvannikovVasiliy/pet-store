package com.pet.pojo;

import com.pet.dto.CityDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Builder
@Data
@AllArgsConstructor
public class StoreModel {
    @NotEmpty
    @Size(max = 50)
    public String name;

    @NotEmpty
    @Size(max = 50)
    public String cityName;

    public CityDto city;
}
