package com.rogers.account.management.dtos.api;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

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
