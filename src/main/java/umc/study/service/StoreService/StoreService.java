package umc.study.service.StoreService;

import umc.study.domain.Review;
import umc.study.domain.Store;
import umc.study.web.dto.ReviewRequestDTO;
import umc.study.web.dto.StoreRequestDTO;


public interface StoreService {

    // 특정 지역에 가게 추가하기 API
    Store addStoreToRegion(StoreRequestDTO storeRequestDTO);

    Review addReviewToStore(ReviewRequestDTO reviewDTO, Long storeId);
}
