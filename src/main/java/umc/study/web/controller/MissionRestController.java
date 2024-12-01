package umc.study.web.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import umc.study.ApiPayload.ApiResponse;
import umc.study.service.MissionService.MissionCommandService;
import umc.study.web.dto.MissionRequestDTO;

@RestController
@RequiredArgsConstructor
@RequestMapping("/missions")
public class MissionRestController {

    private final MissionCommandService missionCommandService;

    @PostMapping
    public ResponseEntity<ApiResponse<?>> addMission(@RequestBody @Valid MissionRequestDTO missionRequest) {
        missionCommandService.addMission(missionRequest); // 서비스 호출
        return ResponseEntity.ok(ApiResponse.onSuccess("Mission added successfully!"));
    }
}

