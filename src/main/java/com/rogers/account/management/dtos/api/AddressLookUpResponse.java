/*
 * Copyright (c) 2024.
 * This is a assignment project by Gaurav Kumar for client assessment.
 */

package com.rogers.account.management.dtos.api;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * The type Address look up response.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AddressLookUpResponse {
    @JsonProperty("post code")
    private String postalCode;
    private String country;
    @JsonProperty("country abbreviation")
    private String countryCode;
    private List<PlaceLookUpResponse> places;
}
