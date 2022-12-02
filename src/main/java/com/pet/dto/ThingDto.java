package com.pet.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ThingDto {
    public String name;
    public String nameStore;
    public String nameCategory;
    public String nameCountry;
    public Integer price;
}
