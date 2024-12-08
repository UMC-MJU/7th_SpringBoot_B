package umc.study.validation.annotation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import umc.study.validation.validator.PageValidator;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.PARAMETER, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = PageValidator.class)
public @interface Pageable {
    String message() default "Invalid page number. Page number must be greater than 0.";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
