/*
 * Copyright (c) 2024.
 * This is a assignment project by Gaurav Kumar for client assessment.
 */

package com.rogers.account.management.dtos.response;

import com.rogers.account.management.enums.AccountStatus;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateAccountResponse {
    @Schema(example="A9K09O")
    private String accountId;
    @Schema(example="Active")
    private AccountStatus status;
    @Schema(example="7289")
    private String securityPIN;

}
