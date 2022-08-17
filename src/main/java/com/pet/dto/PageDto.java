package com.pet.dto;

import lombok.Data;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

@Data
public class PageDto {
    private int pageNumber = 0;
    private int pageSize = 3;
    private Sort.Direction sortDirection = Sort.Direction.DESC;
    private String sortBy = "id";

    public Pageable getPageable() {
        return PageRequest.of(
                pageNumber,
                pageSize,
                sortDirection,
                sortBy
        );
    }
}
