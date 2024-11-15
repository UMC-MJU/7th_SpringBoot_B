package com.example.study.converter;

import com.example.study.web.dto.TempResponse;

public class TempConverter {

    //TempResponse라는 클래스 안에 있는 TempTestDto를 반환하는 메서드
    public static TempResponse.TempTestDto toTempTestDto(){
        return TempResponse.TempTestDto.builder()
                .testString("this is test.")
                .build();
    }

    public static TempResponse.TempExceptionDTO toTempExceptionDto(Integer flag){
        return TempResponse.TempExceptionDTO.builder()
                .flag(flag)
                .build();
    }
}
