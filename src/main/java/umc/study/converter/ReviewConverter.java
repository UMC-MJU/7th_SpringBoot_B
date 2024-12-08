package umc.study.converter;

import umc.study.domain.Review;
import umc.study.web.dto.ReviewResponseDTO;

import java.util.List;
import java.util.stream.Collectors;

public class ReviewConverter {

    // Review 엔티티 리스트를 ReviewResponseDTO로 변환
    public static List<ReviewResponseDTO> convertToDTO(List<Review> reviews) {
        return reviews.stream()
                .map(review -> ReviewResponseDTO.builder()
                        .id(review.getId()) // Review ID
                        .title(review.getTitle()) // 제목
                        .score(review.getScore()) // 평점
                        .body(review.getBody()) // 리뷰 내용
                        .storeName(review.getStore().getName()) // 상점 이름
                        .build())
                .collect(Collectors.toList());
    }
}
