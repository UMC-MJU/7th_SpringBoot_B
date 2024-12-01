package umc.study.web.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class MemberMissionResponseDTO {
    private Long missionId; // 미션 ID
    private String status;  // 미션 상태
}
