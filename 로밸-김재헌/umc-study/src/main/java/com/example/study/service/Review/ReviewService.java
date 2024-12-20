package com.example.study.service.Review;

import com.example.study.domain.Member;
import com.example.study.domain.Review;
import com.example.study.web.dto.ReviewRequest;
import org.springframework.data.domain.Page;


public interface ReviewService {

    public void register(Member member, Long storeId, ReviewRequest.registerReview request);

    public Page<Review> getMyReviews(Member member, int page);


}
