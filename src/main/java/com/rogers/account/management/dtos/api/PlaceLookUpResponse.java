/*
 * Copyright (c) 2024.
 * This is a assignment project by Gaurav Kumar for client assessment.
 */

package com.rogers.account.management.dtos.api;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * The type Place look up response.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PlaceLookUpResponse {
    @JsonProperty("place name")
    private String placeName;
    private String longitude;
    private String state;
    @JsonProperty("state abbreviation")
    private String stateCode;
    private String latitude;
}
