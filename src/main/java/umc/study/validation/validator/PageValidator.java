package umc.study.validation.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import umc.study.validation.annotation.Pageable;

public class PageValidator implements ConstraintValidator<Pageable, Integer> {

    @Override
    public boolean isValid(Integer value, ConstraintValidatorContext context) {
        if (value != null && value > 0) {
            return true;
        }
        return false;
    }
}
