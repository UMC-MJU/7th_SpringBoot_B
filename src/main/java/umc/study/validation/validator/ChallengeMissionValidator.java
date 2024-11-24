package umc.study.validation.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import umc.study.apiPayload.code.status.ErrorStatus;
import umc.study.repository.MemberMissionRepository;
import umc.study.validation.annotation.ValidChallengeMission;

import java.util.List;

@Component
@RequiredArgsConstructor
public class ChallengeMissionValidator implements ConstraintValidator<ValidChallengeMission, List<Long>> {

    private final MemberMissionRepository memberMissionRepository;

    @Override
    public void initialize(ValidChallengeMission constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(List<Long> missionIds, ConstraintValidatorContext context) {
        // 모든 missionId가 유효한지 확인
        boolean isValid = missionIds.stream()
                .allMatch(missionId -> memberMissionRepository.existsByMember_Id(missionId));

        if (!isValid) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(ErrorStatus.MISSION_ALREADY_CHALLENGED.toString())
                    .addConstraintViolation();
        }

        return isValid;
    }
}
