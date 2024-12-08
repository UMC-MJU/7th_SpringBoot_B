package umc.study.converter;

import org.springframework.data.domain.Page;
import umc.study.domain.Review;
import umc.study.web.dto.StoreResponseDTO;

import java.util.List;
import java.util.stream.Collectors;

public class StoreConverter {

    public static StoreResponseDTO.ReviewPreViewDTO reviewPreViewDTO(Review review) {
        // Member가 null일 경우 기본값 "Unknown" 사용
        String ownerNickname = (review.getMember() != null) ? review.getMember().getName() : "Unknown";

        return StoreResponseDTO.ReviewPreViewDTO.builder()
                .ownerNickname(ownerNickname)
                .score(review.getScore())
                .createdAt(review.getCreatedAt().toLocalDate())
                .body(review.getBody())
                .build();
    }

    public static StoreResponseDTO.ReviewPreViewListDTO reviewPreViewListDTO(Page<Review> reviewList) {
        List<StoreResponseDTO.ReviewPreViewDTO> reviews = reviewList.stream()
                .map(StoreConverter::reviewPreViewDTO)
                .collect(Collectors.toList());

        return StoreResponseDTO.ReviewPreViewListDTO.builder()
                .isLast(reviewList.isLast())
                .isFirst(reviewList.isFirst())
                .totalPage(reviewList.getTotalPages())
                .totalElements(reviewList.getTotalElements())
                .listSize(reviews.size())
                .reviewList(reviews)
                .build();
    }
}

