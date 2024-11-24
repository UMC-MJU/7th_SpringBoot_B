package com.example.study.validation.annotation;

import com.example.study.validation.validator.CategoriesExistValidator;
import com.example.study.validation.validator.MissionStartAbleValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = MissionStartAbleValidator.class)
@Target({ElementType.CONSTRUCTOR, ElementType.TYPE, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface MissionStartAble {

    String message() default "이미 등록된 미션이므로 미션 시작이 불가능합니다.";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
