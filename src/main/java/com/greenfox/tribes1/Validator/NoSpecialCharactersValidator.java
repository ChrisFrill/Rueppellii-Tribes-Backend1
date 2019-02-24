package com.greenfox.tribes1.Validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class NoSpecialCharactersValidator implements ConstraintValidator<NoSpecialCharacters, String> {
  @Override
  public void initialize(NoSpecialCharacters constraintAnnotation) {

  }

  @Override
  public boolean isValid(String value, ConstraintValidatorContext context) {
    return value.matches("^[_A-z0-9]*((-|\\s)*[_A-z0-9])*$");
  }
}
