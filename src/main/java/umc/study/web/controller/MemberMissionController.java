package umc.study.web.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import umc.study.ApiPayload.ApiResponse;
import umc.study.service.MissionService.MemberMissionService;
import umc.study.web.dto.MemberMissionRequestDTO;

@RestController
@RequiredArgsConstructor
@RequestMapping("/member-missions")
@Tag(name = "MemberMission API", description = "APIs for member mission challenges")
public class MemberMissionController {

    private final MemberMissionService memberMissionService;

    @PostMapping
    @Operation(summary = "Challenge a mission", description = "Allows a member to challenge a specific mission.")
    public ResponseEntity<ApiResponse<?>> challengeMission(@RequestBody @Valid MemberMissionRequestDTO request) {
        memberMissionService.challengeMission(request);
        return ResponseEntity.ok(ApiResponse.onSuccess("Mission successfully challenged!"));
    }
}

