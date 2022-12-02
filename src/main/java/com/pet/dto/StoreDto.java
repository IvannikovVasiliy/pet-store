package com.pet.dto;

import com.pet.entity.City;
import com.pet.entity.Country;
import com.pet.entity.StoreEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.UUID;

@Builder
@AllArgsConstructor
@NoArgsConstructor
public class StoreDto {
    @NotEmpty
    @Size(max = 50)
    public String name;

    @NotEmpty
    @Size(max = 50)
    public String cityName;

    public static StoreDto toDto(StoreEntity store) {
        return StoreDto
                .builder()
                .name(store.getName())
                .cityName(store.getCity().getName())
                .build();
    }

    public StoreEntity toEntity() {
        StoreEntity store = new StoreEntity();
        store.setName(name);
        store.setCity(
                new City(1L, cityName, new Country(1L, "Russia"))
        );
        return store;
    }
}
