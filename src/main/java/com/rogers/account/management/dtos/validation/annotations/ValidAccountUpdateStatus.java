package com.rogers.account.management.dtos.validation.annotations;

import com.rogers.account.management.dtos.validation.AccountStatusCreationValidator;
import com.rogers.account.management.dtos.validation.AccountStatusUpdateValidator;
import com.rogers.account.management.enums.AccountStatus;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target({ METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER, TYPE_USE })
@Retention(RUNTIME)
@Documented
@Constraint(validatedBy = AccountStatusUpdateValidator.class)
public @interface ValidAccountUpdateStatus {
    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    Class<AccountStatus> enumClass();

    String message();
}
