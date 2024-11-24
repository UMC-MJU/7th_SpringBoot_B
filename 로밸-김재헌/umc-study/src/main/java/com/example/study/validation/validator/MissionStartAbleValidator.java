package com.example.study.validation.validator;

import com.example.study.exception.ErrorStatus;
import com.example.study.service.mission.MissionService;
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
public class MissionStartAbleValidator implements ConstraintValidator<MissionStartAble, MemberMissionRequest.StartMission> {
    private final MissionService missionService;

    @Override
    public void initialize(MissionStartAble constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    //잘 동작하긴 하나... 디버깅해보면 이 부분이 2번 실행되고 있음.
    @Override
    public boolean isValid(MemberMissionRequest.StartMission startMission, ConstraintValidatorContext context) {
        boolean isValid = !missionService.exist(startMission.getMember(), startMission.getMissionId());
        log.info("isValid: "+ isValid);

        if(!isValid){
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(ErrorStatus.MISSION_CONFLICT.toString()).addConstraintViolation();
        }

        return isValid;
    }
}
