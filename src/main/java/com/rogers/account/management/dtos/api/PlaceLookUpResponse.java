package com.rogers.account.management.dtos.api;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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
