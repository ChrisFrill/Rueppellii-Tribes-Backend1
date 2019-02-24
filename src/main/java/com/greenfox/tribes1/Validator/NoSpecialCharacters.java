package com.greenfox.tribes1.Validator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = NoSpecialCharactersValidator.class)
@Target({ ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface NoSpecialCharacters {
  String message() default "Name can't contain special characters";
  Class<?>[] groups() default {};
  Class<? extends Payload>[] payload() default {};
}
