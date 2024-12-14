package com.example.study.converter;

import com.example.study.domain.MemberMission;
import com.example.study.domain.Mission;
import com.example.study.web.dto.MemberMissionRequest;
import com.example.study.web.dto.MemberMissionResponse;

public class MissionConverter {

    public static MemberMissionResponse.memberMissionPreview toMemberMissionPreview(MemberMission memberMission){
        Mission mission = memberMission.getMission();
        return new MemberMissionResponse.memberMissionPreview(mission.getStore().getName(), mission.getReward());
    }
}
