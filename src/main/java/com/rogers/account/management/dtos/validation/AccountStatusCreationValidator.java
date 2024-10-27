package com.rogers.account.management.dtos.validation;

import com.rogers.account.management.dtos.validation.annotations.ValidAccountCreationStatus;
import com.rogers.account.management.enums.AccountStatus;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.Arrays;
import java.util.List;

public class AccountStatusCreationValidator implements ConstraintValidator<ValidAccountCreationStatus, CharSequence> {
    private List<String> acceptedValues;

    @Override
    public void initialize(ValidAccountCreationStatus annotation) {
        acceptedValues = Arrays.asList(AccountStatus.REQUESTED.name());
    }

    @Override
    public boolean isValid(CharSequence value, ConstraintValidatorContext context) {
        if (value == null) {
            return true;
        }
        return acceptedValues.contains(value.toString());
    }
}