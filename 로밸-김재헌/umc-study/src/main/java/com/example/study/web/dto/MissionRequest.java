package com.example.study.web.dto;

import lombok.Getter;

import java.time.LocalDate;

public class MissionRequest {

    @Getter
    public static class registerMission{
        private Integer reward;
        private LocalDate deadLine;
        private String missionSpec;
    }
}
