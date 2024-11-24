package umc.study.validation.annotation;



import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import umc.study.validation.validator.ChallengeMissionValidator;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = ChallengeMissionValidator.class)
@Target({ ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidChallengeMission {
    String message() default "이미 도전중인 미션입니다.";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
