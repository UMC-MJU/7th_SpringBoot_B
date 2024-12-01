package umc.study.service.ReviewService;

import umc.study.web.dto.ReviewRequestDTO;
import umc.study.domain.Review;

public interface ReviewCommandService {

    Review addReview(ReviewRequestDTO request);
}
