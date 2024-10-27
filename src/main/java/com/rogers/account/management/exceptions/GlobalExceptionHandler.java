package com.rogers.account.management.exceptions;

import com.rogers.account.management.exceptions.types.*;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.rogers.account.management.exceptions.constants.ErrorCodes.*;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(AccountAlreadyExistsException.class)
    public ResponseEntity<Object> handleAccountAlreadyExistsException(AccountAlreadyExistsException exception, WebRequest webRequest) {
        GlobalErrorResponse errorResponse = new GlobalErrorResponse();
        errorResponse.setStatus(HttpStatus.CONFLICT);
        errorResponse.setMessage(exception.getMessage());
        errorResponse.setErrorCodes(Arrays.asList(ACCOUNT_ALREADY_EXISTS_ERROR));
        ResponseEntity<Object> entity = new ResponseEntity<>(errorResponse, HttpStatus.CONFLICT);
        return entity;
    }

    @ExceptionHandler(AddressLookUpException.class)
    public ResponseEntity<Object> handleAddressLookUpException(AddressLookUpException exception, WebRequest webRequest) {
        GlobalErrorResponse errorResponse = new GlobalErrorResponse();
        errorResponse.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
        errorResponse.setMessage(exception.getMessage());
        errorResponse.setErrorCodes(Arrays.asList(ADDRESS_LOOKUP_ERROR));
        ResponseEntity<Object> entity = new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
        return entity;
    }

    @ExceptionHandler(InvalidCountryAndPostalCombinationException.class)
    public ResponseEntity<Object> handleInvalidCountryAndPostalCombinationException(InvalidCountryAndPostalCombinationException exception, WebRequest webRequest) {
        GlobalErrorResponse errorResponse = new GlobalErrorResponse();
        errorResponse.setStatus(HttpStatus.BAD_REQUEST);
        errorResponse.setMessage(exception.getMessage());
        errorResponse.setErrorCodes(Arrays.asList(INVALID_COUNTRY_POSTAL_COMBINATION_ERROR));
        ResponseEntity<Object> entity = new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
        return entity;
    }

    @ExceptionHandler(AccountNotFoundException.class)
    public ResponseEntity<Object> handleAccountNotFoundException(AccountNotFoundException exception, WebRequest webRequest) {
        GlobalErrorResponse errorResponse = new GlobalErrorResponse();
        errorResponse.setStatus(HttpStatus.NOT_FOUND);
        errorResponse.setMessage(exception.getMessage());
        errorResponse.setErrorCodes(Arrays.asList(ACCOUNT_NOT_FOUND));
        ResponseEntity<Object> entity = new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
        return entity;
    }

    @ExceptionHandler(AccountStatusException.class)
    public ResponseEntity<Object> handleAccountStatusException(AccountStatusException exception, WebRequest webRequest) {
        GlobalErrorResponse errorResponse = new GlobalErrorResponse();
        errorResponse.setStatus(HttpStatus.BAD_REQUEST);
        errorResponse.setMessage(exception.getMessage());
        errorResponse.setErrorCodes(Arrays.asList(INVALID_ACCOUNT_STATUS));
        ResponseEntity<Object> entity = new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
        return entity;
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleGlobalException(Exception exception, WebRequest webRequest) {
        GlobalErrorResponse errorResponse = new GlobalErrorResponse();
        errorResponse.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
        errorResponse.setMessage(exception.getMessage());
        errorResponse.setErrorCodes(Arrays.asList(UNKNOWN_ERROR));
        ResponseEntity<Object> entity = new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
        return entity;
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> handleDTOValidationExceptions(MethodArgumentNotValidException exception, WebRequest webRequest) {
        GlobalErrorResponse errorResponse = new GlobalErrorResponse();
        errorResponse.setStatus(HttpStatus.BAD_REQUEST);
        List<String> errorLists = new ArrayList<>();
        exception.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errorLists.add(errorMessage);
        });
        errorResponse.setMessage(REQUEST_VALIDATION_ERROR);
        errorResponse.setErrorCodes(errorLists);
        ResponseEntity<Object> entity = new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
        return entity;
    }

    @ExceptionHandler(EmailAlreadyInUseException.class)
    public ResponseEntity<Object> handleEmailAlreadyInUseException(EmailAlreadyInUseException exception, WebRequest webRequest) {
        GlobalErrorResponse errorResponse = new GlobalErrorResponse();
        errorResponse.setStatus(HttpStatus.BAD_REQUEST);
        errorResponse.setMessage(exception.getMessage());
        errorResponse.setErrorCodes(Arrays.asList(EMAIL_ALREADY_IN_USE));
        ResponseEntity<Object> entity = new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
        return entity;
    }

    @ExceptionHandler(AccountIdOrEmailException.class)
    public ResponseEntity<Object> handleAccountIdOrEmailException(AccountIdOrEmailException exception, WebRequest webRequest) {
        GlobalErrorResponse errorResponse = new GlobalErrorResponse();
        errorResponse.setStatus(HttpStatus.BAD_REQUEST);
        errorResponse.setMessage(exception.getMessage());
        errorResponse.setErrorCodes(Arrays.asList(ACCOUNT_ID_OR_EMAIL_REQUIRED));
        ResponseEntity<Object> entity = new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
        return entity;
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<Object> handleDTOValidationExceptions(ConstraintViolationException exception, WebRequest webRequest) {
        GlobalErrorResponse errorResponse = new GlobalErrorResponse();
        errorResponse.setStatus(HttpStatus.BAD_REQUEST);
        errorResponse.setMessage(exception.getMessage());
        errorResponse.setErrorCodes(Arrays.asList(REQUEST_VALIDATION_ERROR));
        ResponseEntity<Object> entity = new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
        return entity;
    }

}
