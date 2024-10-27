package com.rogers.account.management.controllers;

import com.rogers.account.management.dtos.request.CreateAccountRequest;
import com.rogers.account.management.dtos.response.AccountCountResponse;
import com.rogers.account.management.dtos.response.CreateAccountResponse;
import com.rogers.account.management.dtos.response.GenericResponse;
import com.rogers.account.management.dtos.validation.annotations.ValidAccountCreationStatus;
import com.rogers.account.management.dtos.validation.annotations.ValidAccountUpdateStatus;
import com.rogers.account.management.entities.Account;
import com.rogers.account.management.enums.AccountStatus;
import com.rogers.account.management.exceptions.GlobalErrorResponse;
import com.rogers.account.management.exceptions.types.*;
import com.rogers.account.management.services.AccountService;
import io.micrometer.common.util.StringUtils;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Objects;

import static com.rogers.account.management.commons.AppConstant.*;

@Validated
@RestController
@RequestMapping(BACK_SLASH + API + BACK_SLASH + VERSION + BACK_SLASH + ACCOUNT)
public class AccountController {

    @Autowired
    private AccountService accountService;

    @PostMapping
    @Operation(summary = CREATE_ACCOUNT_SUMMARY, description = CREATE_ACCOUNT_DESCRIPTION, tags = {ACCOUNT_OPERATION})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Created Successfully", content = { @Content(mediaType = APPLICATION_JSON, schema = @Schema(implementation = CreateAccountResponse.class)) }),
            @ApiResponse(responseCode = "400", description = "Invalid payload provided", content = { @Content(mediaType = APPLICATION_JSON, schema = @Schema(implementation = GlobalErrorResponse.class)) }),
            @ApiResponse(responseCode = "500", description = "Internal server error", content = { @Content(mediaType = APPLICATION_JSON, schema = @Schema(implementation = GlobalErrorResponse.class)) }) })
    public ResponseEntity<?> createAccount(@Valid @RequestBody CreateAccountRequest request) {
        Account existingAccount = getAccountByEmail(request);
        if (Objects.nonNull(existingAccount)) {
            throw new AccountAlreadyExistsException("Account with email " + request.getEmail() + " already exists.");
        }
        CreateAccountResponse newAccount = accountService.createAccount(request);
        return new ResponseEntity<>(newAccount, HttpStatus.CREATED);
    }

    @PutMapping(BACK_SLASH + "{" + ACCOUNT_ID + "}")
    @Operation(summary = UPDATE_ACCOUNT_SUMMARY, description = UPDATE_ACCOUNT_DESCRIPTION, tags = {ACCOUNT_OPERATION})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Updated Successfully", content = { @Content(mediaType = APPLICATION_JSON, schema = @Schema(implementation = GenericResponse.class)) }),
            @ApiResponse(responseCode = "400", description = "Invalid payload provided", content = { @Content(mediaType = APPLICATION_JSON, schema = @Schema(implementation = GlobalErrorResponse.class)) }),
            @ApiResponse(responseCode = "500", description = "Internal server error", content = { @Content(mediaType = APPLICATION_JSON, schema = @Schema(implementation = GlobalErrorResponse.class)) }) })
    public ResponseEntity<?> updateAccount(@PathVariable(ACCOUNT_ID) String accountId,
            @Valid
            @Size(max = 20, message = "Name should not be more than 20 character long")
            @Schema(example="Gaurav Kumar")
            @RequestParam(value = "name", required = false)
            String name,

            @Valid
            @Email(message = "Please provide a valid email id")
            @Schema(example="gaurav.mute@gmail.com")
            @RequestParam(value = "email", required = false)
            String email,

            @Valid
            @Pattern(regexp ="US|DE|ES|FR", message = "System currently supports these countries United States, Germany, Spain, France")
            @Schema(example="US")
            @RequestParam(value = "country", required = false)
            String country,

            @Valid
            @Size(max = 5, message = "Please enter a valid postal code")
            @Schema(example="60661")
            @RequestParam(value = "postalCode", required = false)
            String postalCode,

            @Valid
            @Schema(example="22")
            @RequestParam(value = "age", required = false)
            int age,

            @Valid
            @ValidAccountUpdateStatus(enumClass = AccountStatus.class, message = "Invalid account status provided")
            @Schema(example="ACTIVE")
            @RequestParam(value = "status", required = false)
            String status) {
        Account existingAccount = getAccountByAccountId(accountId);
        if (Objects.isNull(existingAccount)) {
            throw new AccountNotFoundException("Account with id " + accountId + " not found.");
        }
        if (existingAccount.getEmail().equals(email)) {
            throw new EmailAlreadyInUseException("Email id " + email + " is already bounded to other account");
        }
        accountService.updateAccount(existingAccount, name, email, country, postalCode, age, status, new Date());
        return new ResponseEntity<>(new GenericResponse(HttpStatus.OK, ACCOUNT_UPDATED), HttpStatus.OK);
    }

    @GetMapping
    @Operation(summary = GET_ACCOUNT_SUMMARY, description = GET_ACCOUNT_DESCRIPTION, tags = {ACCOUNT_OPERATION})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Fetched Successfully", content = { @Content(mediaType = APPLICATION_JSON, schema = @Schema(implementation = Account.class)) }),
            @ApiResponse(responseCode = "400", description = "Invalid payload provided", content = { @Content(mediaType = APPLICATION_JSON, schema = @Schema(implementation = GlobalErrorResponse.class)) }),
            @ApiResponse(responseCode = "500", description = "Internal server error", content = { @Content(mediaType = APPLICATION_JSON, schema = @Schema(implementation = GlobalErrorResponse.class)) }) })
    public ResponseEntity<?> getAccount(
            @Valid
            @Schema(example="IdeUn0")
            @RequestParam(value = ACCOUNT_ID, required = false) String accountId,

            @Valid
            @Email(message = "Please provide a valid email id")
            @Schema(example="gaurav.mute@gmail.com")
            @RequestParam(value = EMAIL, required = false)
            String email) {
        if (StringUtils.isBlank(accountId) && StringUtils.isBlank(email)) {
            throw new AccountIdOrEmailException(EITHER_ACCOUNT_ID_OR_MAIL);
        }
        Account account = accountService.getAccount(accountId, email);
        if (Objects.isNull(account)) {
            throw new AccountNotFoundException("Account not found with provided details");
        }
        return new ResponseEntity<>(account, HttpStatus.OK);
    }

    @GetMapping(BACK_SLASH + COUNT)
    @Operation(summary = GET_ACCOUNT_COUNT_SUMMARY, description = GET_ACCOUNT_COUNT_DESCRIPTION, tags = {ACCOUNT_OPERATION})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Fetched Successfully", content = { @Content(mediaType = APPLICATION_JSON, schema = @Schema(implementation = AccountCountResponse.class)) }),
            @ApiResponse(responseCode = "500", description = "Internal server error", content = { @Content(mediaType = APPLICATION_JSON, schema = @Schema(implementation = GlobalErrorResponse.class)) }) })
    public ResponseEntity<?> getAccountCount() {
        List<AccountCountResponse> response = accountService.getAccountCount();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping(BACK_SLASH + DELETE + BACK_SLASH + "{" + ACCOUNT_ID + "}" + BACK_SLASH + "{" + SECURITY_PIN + "}")
    @Operation(summary = DELETE_ACCOUNT_SUMMARY, description = DELETE_ACCOUNT_DESCRIPTION, tags = {ACCOUNT_OPERATION})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Deleted Successfully", content = { @Content(mediaType = APPLICATION_JSON, schema = @Schema(implementation = GenericResponse.class)) }),
            @ApiResponse(responseCode = "400", description = "Invalid payload provided", content = { @Content(mediaType = APPLICATION_JSON, schema = @Schema(implementation = GlobalErrorResponse.class)) }),
            @ApiResponse(responseCode = "500", description = "Internal server error", content = { @Content(mediaType = APPLICATION_JSON, schema = @Schema(implementation = GlobalErrorResponse.class)) }) })
    public ResponseEntity<?> deleteAccount(@PathVariable(ACCOUNT_ID) String accountId, @PathVariable(SECURITY_PIN) String securityPIN) {
        Account existingAccount = getAccountByAccountIdAndSecurityPIN(accountId, securityPIN);
        if (Objects.isNull(existingAccount)) {
            throw new AccountNotFoundException(ACCOUNT_NOT_FOUND);
        }
        if (existingAccount.getStatus() != AccountStatus.IN_ACTIVE) {
            throw new AccountStatusException(ACCOUNT_DELETION_STATUS_ERROR);
        }
        accountService.deleteAccount(existingAccount);
        return new ResponseEntity<>(new GenericResponse(HttpStatus.OK, ACCOUNT_DELETED), HttpStatus.OK);
    }

    private Account getAccountByAccountIdAndSecurityPIN(String accountId, String securityPIN) {
        return accountService.getAccountByAccountIdAndSecurityPIN(accountId, securityPIN);
    }

    private Account getAccountByEmail(CreateAccountRequest request) {
        return accountService.getAccountByEmail(request.getEmail());
    }

    private Account getAccountByAccountId(String accountId) {
        return accountService.getAccountByAccountId(accountId);
    }
}
