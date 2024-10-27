package com.rogers.account.management.dtos.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccountCountResponse {
    @Schema(example="US")
    private String country;
    @Schema(example="10")
    private int count;
    List<State> states;

}
