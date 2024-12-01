package umc.study.web.dto;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;
import umc.study.validation.annotation.StoreExists;

import java.time.LocalDate;

@Getter
@Setter
public class MissionRequestDTO {

    @NotBlank(message = "Mission specification is mandatory")
    private String missionSpec; // 미션 내용

    @NotNull(message = "Reward is mandatory")
    private Integer reward; // 보상 포인트

    @NotNull(message = "Deadline is mandatory")
    @FutureOrPresent(message = "Deadline must be today or in the future")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate deadline; // 마감 기한

    @NotNull(message = "Store ID is mandatory")
    @StoreExists(message = "Store does not exist") // 가게 존재 여부 검증
    private Long storeId; // 가게 ID
}
