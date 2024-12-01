package umc.study.validation.annotation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import umc.study.validation.validator.StoreExistsValidator;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = StoreExistsValidator.class)
public @interface StoreExists {
    String message() default "Store does not exist";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
