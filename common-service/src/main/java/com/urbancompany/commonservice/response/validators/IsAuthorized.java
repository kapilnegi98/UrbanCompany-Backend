package com.urbancompany.commonservice.response.validators;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import com.urbancompany.commonservice.enums.UserRole;
import com.urbancompany.commonservice.response.validators.impl.IsAuthorizedValidator;

@Documented
@Retention(RUNTIME)
@Constraint(validatedBy = IsAuthorizedValidator.class)
@Target({ FIELD, PARAMETER, METHOD })
public @interface IsAuthorized {

	String message() default "UnAuthorized!!";

	UserRole[] authorizedRoles();

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};

}
