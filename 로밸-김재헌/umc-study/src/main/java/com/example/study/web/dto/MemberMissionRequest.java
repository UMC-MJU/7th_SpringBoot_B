package com.example.study.web.dto;

import com.example.study.domain.Member;
import com.example.study.validation.annotation.MissionStartAble;
import lombok.Getter;

public class MemberMissionRequest {

    @Getter
    public static class StartMission {
        private Member member;
        private Long missionId;

        @MissionStartAble
        public StartMission(Member member, Long missionId) {
            this.member = member;
            this.missionId = missionId;
        }
    }
}
