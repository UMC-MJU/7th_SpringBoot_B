package umc.study.web.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Getter
@NoArgsConstructor
public class StoreRequestDTO {

    private String name;       // 가게 이름
    private String address;    // 가게 주소
    private Float score;       // 가게 평점
    private Long regionId;     // 지

    @Getter
    @NoArgsConstructor
    public static class ReviewRequestDTO {
        private String title; // 리뷰 제목
        private String body;  // 리뷰 본문
        private Float score;  // 리뷰 평점
    }

    @Getter
    @NoArgsConstructor
    public static class MissionRequestDTO {
        private String name;    // 미션 이름
        private Integer reward; // 미션 보상
        private LocalDate deadline; // 미션 마감일
    }



}
