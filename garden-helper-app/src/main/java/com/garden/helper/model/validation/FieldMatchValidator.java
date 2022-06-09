package com.garden.helper.model.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.apache.commons.beanutils.BeanUtils;

public class FieldMatchValidator
		implements ConstraintValidator<FieldMatch, Object> {
	
	private String firstFieldName;
	private String secondFieldName;
	private String message;
	
	@Override
	public void initialize(FieldMatch constraintAnnotation) {
		firstFieldName = constraintAnnotation.first();
		secondFieldName = constraintAnnotation.second();
		message = constraintAnnotation.message();
	}

	@Override
	public boolean isValid(Object value, ConstraintValidatorContext context) {
		
		boolean valid = true;
		
		try {
			Object firstObj = BeanUtils.getProperty(value, firstFieldName);
            Object secondObj = BeanUtils.getProperty(value, secondFieldName);
            
            valid = firstObj == null && secondObj == null ||
            		firstObj != null && firstObj.equals(secondObj);
		} catch(Exception e) {
			e.getMessage();
		}
		
		if (!valid){
            context.buildConstraintViolationWithTemplate(message)
                    .addPropertyNode(firstFieldName)
                    .addConstraintViolation()
                    .disableDefaultConstraintViolation();
        }
		
		return valid;
	}
}
