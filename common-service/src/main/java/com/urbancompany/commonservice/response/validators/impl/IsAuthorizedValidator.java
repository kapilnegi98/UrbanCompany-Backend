package com.urbancompany.commonservice.response.validators.impl;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import com.urbancompany.commonservice.config.CommonFileProperties;
import com.urbancompany.commonservice.exception.AuthorizationError;
import com.urbancompany.commonservice.response.validators.IsAuthorized;

public class IsAuthorizedValidator implements ConstraintValidator<IsAuthorized, String>{

	@Autowired
	private CommonFileProperties commonFileProps;
	
	private Set<String> authorizedRoleValues;
	
	@Override
	public void initialize(IsAuthorized constraintAnnotation) {
		ConstraintValidator.super.initialize(constraintAnnotation);
		authorizedRoleValues = new HashSet<>();
		Arrays.stream(constraintAnnotation.authorizedRoles())
			.forEach(role -> authorizedRoleValues.add(role.name()));
	}
	
	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		if(commonFileProps.isEnableAuth() && !authorizedRoleValues.contains(value)) {
			throw new AuthorizationError();
		}
		return true;
	}

}
