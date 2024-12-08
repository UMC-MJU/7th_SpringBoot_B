package umc.study.web.dto;

import lombok.Builder;
import lombok.Getter;
import umc.study.domain.Review;

@Getter
@Builder
public class ReviewResponseDTO {

    private Long id;
    private String title;
    private Float score;
    private String body;
    private String memberName;
    private String storeName;

    public static ReviewResponseDTO fromEntity(Review review) {
        return ReviewResponseDTO.builder()
                .id(review.getId())
                .title(review.getTitle())
                .score(review.getScore())
                .body(review.getBody())
                .memberName(review.getMember().getName())
                .storeName(review.getStore().getName())
                .build();
    }
}
