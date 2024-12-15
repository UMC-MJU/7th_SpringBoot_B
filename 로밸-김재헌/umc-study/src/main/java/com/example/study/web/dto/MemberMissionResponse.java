package com.example.study.web.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;


public class MemberMissionResponse {
    @Getter
    @RequiredArgsConstructor
    public static class memberMissionPreview{
        private final String storeName;
        private final Integer reward;
    }

}
