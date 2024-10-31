/*
 * Copyright (c) 2024.
 * This is a assignment project by Gaurav Kumar for client assessment.
 */

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

/**
 * The interface Valid account update status.
 */
@Target({ METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER, TYPE_USE })
@Retention(RUNTIME)
@Documented
@Constraint(validatedBy = AccountStatusUpdateValidator.class)
public @interface ValidAccountUpdateStatus {
    /**
     * Groups class [ ].
     *
     * @return the class [ ]
     */
    Class<?>[] groups() default {};

    /**
     * Payload class [ ].
     *
     * @return the class [ ]
     */
    Class<? extends Payload>[] payload() default {};

    /**
     * Enum class class.
     *
     * @return the class
     */
    Class<AccountStatus> enumClass();

    /**
     * Message string.
     *
     * @return the string
     */
    String message();
}
