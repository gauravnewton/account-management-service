/*
 * Copyright (c) 2024.
 * This is a assignment project by Gaurav Kumar for client assessment.
 */

package com.rogers.account.management.dtos.request;

import com.rogers.account.management.dtos.validation.annotations.ValidAccountCreationStatus;
import com.rogers.account.management.enums.AccountStatus;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * The type Create account request.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CreateAccountRequest {
    @NotNull(message = "Name should not be blank")
    @Size(max = 20, message = "Name should not be more than 20 character long")
    @Schema(example="Gaurav Kumar")
    private String name;
    @NotNull(message = "Email should not be blank")
    @Email(message = "Please provide a valid email id")
    @Schema(example="gaurav.mute@gmail.com")
    private String email;
    @NotNull(message = "Country should not be blank")
    @Pattern(regexp ="US|DE|ES|FR", message = "System currently supports these countries United States, Germany, Spain, France")
    @Schema(example="US")
    private String country;
    @NotNull(message = "Postal Code should not be blank")
    @Size(max = 5, message = "Please enter a valid postal code")
    @Schema(example="60661")
    private String postalCode;
    @Schema(example="22")
    private int age;
    @ValidAccountCreationStatus(enumClass = AccountStatus.class, message = "Invalid account status provided")
    @Schema(example="REQUESTED")
    private String status;
}
