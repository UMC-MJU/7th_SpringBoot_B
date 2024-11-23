package umc.study.service.StoreService;

import org.springframework.data.domain.Page;
import umc.study.domain.Review;
import umc.study.domain.Store;
import umc.study.web.dto.StoreRequestDTO;
import umc.study.web.dto.StoreResponseDTO;

import java.util.List;
import java.util.Optional;

public interface StoreQueryService {

    Optional<Store> findStore(Long id);
    List<Store> findStoresByNameAndScore(String name, Float score);
    Page<Review> getReviewList(Long StoreId, Integer page);

    // 가게에 리뷰 추가 메서드
    StoreResponseDTO addReviewToStore(Long storeId, StoreRequestDTO.ReviewRequestDTO request);
    StoreResponseDTO addMissionToStore(Long storeId, StoreRequestDTO.MissionRequestDTO request);
}
