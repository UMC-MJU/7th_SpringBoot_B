package umc.study.web.dto;

import lombok.Getter;

@Getter
public class ReviewRequestDTO {
    Float score;
    String title;
    private Long storeId; // 가게 ID

}
