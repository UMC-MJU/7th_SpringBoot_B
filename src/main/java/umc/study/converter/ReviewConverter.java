package umc.study.converter;

import umc.study.domain.Review;
import umc.study.web.dto.ReviewResponseDTO;

import java.time.LocalDateTime;

public class ReviewConverter {
    public static ReviewResponseDTO toReviewResponseDTO(Review review) {
        return ReviewResponseDTO.builder()
                .reviewId(review.getId())
                .createdAt(LocalDateTime.now())
                .build();
    }
}
