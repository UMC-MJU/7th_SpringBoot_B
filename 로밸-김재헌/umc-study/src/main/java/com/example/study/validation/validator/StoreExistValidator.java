package com.example.study.validation.validator;


import com.example.study.domain.Store;
import com.example.study.exception.ErrorStatus;
import com.example.study.service.store.StoreCommandService;
import com.example.study.validation.annotation.ExistStore;
import com.example.study.validation.annotation.MissionStartAble;
import com.example.study.web.dto.MemberMissionRequest;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class StoreExistValidator implements ConstraintValidator<ExistStore, Long> {
    private final StoreCommandService storeCommandService;

    @Override
    public void initialize(ExistStore constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(Long storeId, ConstraintValidatorContext context) {
        boolean isValid = storeCommandService.exist(storeId);
        log.info("StoreExistValidator isValid: "+ isValid); // 이건 잘 적용됨.

        if(!isValid){
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(ErrorStatus.STORE_NOT_FOUND.toString()).addConstraintViolation();
        }

        return isValid;
    }
}
