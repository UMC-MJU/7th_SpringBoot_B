package umc.study.web.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import umc.study.validation.annotation.StoreExists;

import java.util.List;

@Getter
@Setter
public class ReviewRequestDTO {

    @NotBlank(message = "Title is mandatory")
    private String title;  // 리뷰 제목

    @Min(value = 1, message = "Score should be between 1 and 5")
    @Max(value = 5, message = "Score should be between 1 and 5")
    private Float score;  // 평점

    @NotBlank(message = "Body is mandatory")
    private String body;  // 리뷰 내용

    @StoreExists(message = "Store does not exist") // 가게 존재 여부 검증
    private Long storeId;

    private Long memberId;  // 리뷰를 작성할 회원 ID

    private List<Long> reviewImageIds;  // 리뷰와 연결된 이미지 ID들 (선택 사항)
}
