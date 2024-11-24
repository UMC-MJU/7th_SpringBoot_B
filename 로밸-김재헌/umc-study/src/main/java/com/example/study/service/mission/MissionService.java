package com.example.study.service.mission;

import com.example.study.domain.Member;
import com.example.study.domain.Mission;
import com.example.study.validation.annotation.MissionStartAble;
import com.example.study.web.dto.MemberMissionRequest;
import com.example.study.web.dto.MissionRequest;
import jakarta.validation.Valid;
import org.springframework.validation.annotation.Validated;


@Validated
public interface MissionService {

    public void register(Long storeId, MissionRequest.registerMission request);
    public void registerUser(@Valid @MissionStartAble MemberMissionRequest.StartMission startMission);

    public boolean exist(Member member, Long missionId);
    public boolean exist(Member member, Mission mission);
}
