package com.example.OilGasFieldOperationsSystem.exceptions;

import java.util.Date;

public record ErrorResponse(
        int status,
        String error,
        String message,
        Date timestamp
) {
}
