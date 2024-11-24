package com.example.study.web.controller;

import com.example.study.apiPayload.ApiResponse;
import com.example.study.domain.Member;
import com.example.study.service.member.MemberCommandService;
import com.example.study.service.mission.MissionService;
import com.example.study.validation.annotation.MissionStartAble;
import com.example.study.web.dto.MemberMissionRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Validated
public class MissionRestController {
    private final MissionService missionService;
    private final MemberCommandService memberCommandService;

    @PostMapping("/missions/{mission-id}")
    public ApiResponse<HttpStatus> addMissionToUser(@PathVariable(name = "mission-id") Long missionId){
        Member member = memberCommandService.find(1L);
        MemberMissionRequest.StartMission startMission = new MemberMissionRequest.StartMission(member, missionId);
        missionService.registerUser(startMission);
        return ApiResponse.onSuccess(HttpStatus.OK);
    }

}
