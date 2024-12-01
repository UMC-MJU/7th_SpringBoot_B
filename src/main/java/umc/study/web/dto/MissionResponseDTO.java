package umc.study.web.dto;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@Builder
public class MissionResponseDTO {
    private Long missionId;   // 미션 ID
    private String name;      // 미션 이름
    private Integer reward;   // 미션 보상
    private LocalDate deadline; // 미션 마감일
}
