package com.rogers.account.management.dtos.validation;

import com.rogers.account.management.dtos.validation.annotations.ValidAccountUpdateStatus;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class AccountStatusUpdateValidator implements ConstraintValidator<ValidAccountUpdateStatus, CharSequence> {
    private List<String> acceptedValues;

    @Override
    public void initialize(ValidAccountUpdateStatus annotation) {
        acceptedValues = Stream.of(annotation.enumClass().getEnumConstants())
                .map(Enum::name)
                .collect(Collectors.toList());
    }

    @Override
    public boolean isValid(CharSequence value, ConstraintValidatorContext context) {
        if (value == null) {
            return true;
        }
        return acceptedValues.contains(value.toString());
    }
}