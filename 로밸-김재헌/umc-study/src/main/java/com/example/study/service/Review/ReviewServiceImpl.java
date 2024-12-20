package com.example.study.service.Review;

import com.example.study.domain.Member;
import com.example.study.domain.Review;
import com.example.study.domain.Store;
import com.example.study.repository.review.ReviewRepository;
import com.example.study.repository.store.StoreRepository;
import com.example.study.service.store.StoreCommandService;
import com.example.study.web.dto.ReviewRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReviewServiceImpl implements ReviewService{
    private final ReviewRepository reviewRepository;
    private final StoreCommandService storeCommandService;


    @Override
    public void register(Member member, Long storeId, ReviewRequest.registerReview request){

        Store store = storeCommandService.find(storeId);

        Review review = Review.builder()
                .body(request.getBody())
                .score(request.getScore())
                .title(request.getTitle())
                .member(member)
                .store(store)
                .build();

        // 근데 fk는 어짜피 review가 갖는데... 꼭 양방향이라고 이렇게 연관된 객체들에도 add를 해줘야하나? 영속성 컨텍스트에 영향이 있나?? 잘 모르겠다..
        member.getReviewList().add(review);
        store.getReviewList().add(review);

        reviewRepository.save(review);
    }

    @Override
    public Page<Review> getMyReviews(Member member, int page) {
        return reviewRepository.findAllByMember(member, PageRequest.of(page, 10));
    }


}
