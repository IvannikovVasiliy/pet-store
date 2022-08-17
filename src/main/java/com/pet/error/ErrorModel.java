package com.pet.error;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Data
public class ErrorModel {
    private final String field;
    private final String message;
}
