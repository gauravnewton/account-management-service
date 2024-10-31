/*
 * Copyright (c) 2024.
 * This is a assignment project by Gaurav Kumar for client assessment.
 */

package com.rogers.account.management.dtos.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class State {
    @Schema(example="NY")
    private String state;
    @Schema(example="2")
    private int count;
    private List<Place> places;
}
