package com.example.study.service.Review;

import com.example.study.domain.Member;
import com.example.study.web.dto.ReviewRequest;

public interface ReviewService {

    public void register(Member member, Long storeId, ReviewRequest.registerReview request);


}
