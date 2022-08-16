package com.pet.dto;

import lombok.NonNull;

import javax.validation.constraints.Max;

public class StoreDto {
    @Max(50)
    public String name;
}
