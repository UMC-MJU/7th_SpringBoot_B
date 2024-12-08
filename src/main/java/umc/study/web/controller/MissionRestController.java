package umc.study.web.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import umc.study.ApiPayload.ApiResponse;
import umc.study.service.MissionService.MissionQueryService;
import umc.study.web.dto.MissionResponseDTO;
import umc.study.converter.MissionConverter;
import umc.study.validation.annotation.Pageable;

@RestController
@RequiredArgsConstructor
@RequestMapping("/missions")
@Tag(name = "Mission API", description = "Mission-related APIs")
public class MissionRestController {

    private final MissionQueryService missionQueryService;

    @GetMapping("/{memberId}/ongoing")
    @Operation(summary = "Get ongoing missions", description = "Retrieve ongoing missions of a specific member")
    public ResponseEntity<ApiResponse<MissionResponseDTO.MissionPreViewListDTO>> getOngoingMissions(
            @PathVariable Long memberId,
            @Pageable @RequestParam(defaultValue = "1") Integer page,
            @RequestParam(defaultValue = "10") Integer pageSize) {

        var ongoingMissions = missionQueryService.getOngoingMissions(memberId, page, pageSize);
        var missionListDTO = MissionConverter.missionPreViewListDTO(ongoingMissions);

        return ResponseEntity.ok(ApiResponse.onSuccess(missionListDTO));
    }
}