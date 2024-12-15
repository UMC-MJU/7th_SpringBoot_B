package com.example.study.web.controller;

import com.example.study.apiPayload.ApiResponse;
import com.example.study.domain.Member;
import com.example.study.domain.MemberMission;
import com.example.study.service.member.MemberCommandService;
import com.example.study.service.mission.MissionService;
import com.example.study.web.dto.MemberMissionRequest;
import com.example.study.web.dto.MemberMissionResponse;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@Validated
@RequestMapping("/missions")
public class MissionRestController {
    private final MissionService missionService;
    private final MemberCommandService memberCommandService;

    @PostMapping("/{mission-id}")
    public ApiResponse<HttpStatus> addMissionToUser(@PathVariable(name = "mission-id") Long missionId){
        Member member = memberCommandService.find(1L);
        MemberMissionRequest.StartMission startMission = new MemberMissionRequest.StartMission(member, missionId);
        missionService.registerUser(startMission);
        return ApiResponse.onSuccess(HttpStatus.OK);
    }

    @GetMapping()
    @Operation(summary = "유저 미션 목록 조회")
    public ApiResponse<Page<MemberMissionResponse.memberMissionPreview>> addMissionToUser(@RequestParam(name = "status") String status,
                                                             @RequestParam(name = "page") int page){
        Member member = memberCommandService.find(1L);
        Page<MemberMissionResponse.memberMissionPreview> memberMissions = missionService.getMissions(member, status, page);
        return ApiResponse.onSuccess(memberMissions);
    }

}
