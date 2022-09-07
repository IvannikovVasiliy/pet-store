package com.pet.dto;

import lombok.Builder;

@Builder
public class ThingDto {
    public String name;
    public String nameStore;
    public String nameCategory;
    public String nameCountry;
    public Integer price;
}
