/*
 * Copyright (c) 2024.
 * This is a assignment project by Gaurav Kumar for client assessment.
 */

package com.rogers.account.management.exceptions;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

import java.util.List;

/**
 * The type Global error response.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class GlobalErrorResponse {
    @Schema(example="500")
    private HttpStatus status;
    @Schema(example="error message")
    private String message;
    @Schema(example="error.code.goes.here")
    private List<String> errorCodes;
}
