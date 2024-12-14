package com.example.study.validation.annotation;

import com.example.study.validation.validator.StoreExistValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Target({ElementType.PARAMETER})
@Documented
@Constraint(validatedBy = StoreExistValidator.class)
@Retention(RetentionPolicy.RUNTIME)
public @interface ExistStore {
    String message() default "존재하지 않는 가게입니다.";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
