package umc.study.web.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MemberMissionRequestDTO {

    @Schema(description = "ID of the member who is challenging the mission", example = "1", required = true)
    @NotNull(message = "Member ID is required.")
    private Long memberId;

    @Schema(description = "ID of the mission being challenged", example = "2", required = true)
    @NotNull(message = "Mission ID is required.")
    private Long missionId;
}

