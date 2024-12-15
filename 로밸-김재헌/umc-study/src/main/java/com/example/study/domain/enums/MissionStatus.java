package com.example.study.domain.enums;

import com.example.study.exception.ErrorStatus;
import com.example.study.exception.handler.MissionHandler;

public enum MissionStatus {
    CHALLENGING, COMPLETE
    ;

    public static MissionStatus from(String missionStatus){
        return switch (missionStatus) {
            case "challenging" -> CHALLENGING;
            case "complete" -> COMPLETE;
            default -> throw new MissionHandler(ErrorStatus.MISSION_STATUS_NOT_FOUND);
        };
    }
}
