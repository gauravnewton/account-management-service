package com.rogers.account.management.dtos.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Place {
    @Schema(example="Holtsville")
    private String place;
    @Schema(example="2")
    private int count;
}
