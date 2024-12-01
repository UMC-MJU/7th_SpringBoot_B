package umc.study.service.ReviewService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc.study.domain.Review;
import umc.study.domain.Store;
import umc.study.domain.Member;
import umc.study.domain.ReviewImage;
import umc.study.repository.ReviewRepository;
import umc.study.repository.StoreRepository.StoreRepository;
import umc.study.repository.MemberRepository.MemberRepository;
import umc.study.repository.ReviewImageRepository;
import umc.study.web.dto.ReviewRequestDTO;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ReviewCommandServiceImpl implements ReviewCommandService {

    private final ReviewRepository reviewRepository;
    private final StoreRepository storeRepository;
    private final MemberRepository memberRepository;
    private final ReviewImageRepository reviewImageRepository;  // 리뷰 이미지 관련 리포지토리

    @Override
    @Transactional
    public Review addReview(ReviewRequestDTO request) {
        // 가게와 회원을 찾아옴
        Store store = storeRepository.findById(request.getStoreId())
                .orElseThrow(() -> new RuntimeException("Store not found"));
        Member member = memberRepository.findById(request.getMemberId())
                .orElseThrow(() -> new RuntimeException("Member not found"));

        // 리뷰 객체 생성
        Review review = Review.builder()
                .title(request.getTitle())
                .score(request.getScore())
                .body(request.getBody())
                .store(store)
                .member(member)
                .build();

        // 리뷰 이미지 리스트 처리
        if (request.getReviewImageIds() != null && !request.getReviewImageIds().isEmpty()) {
            List<ReviewImage> reviewImages = reviewImageRepository.findAllById(request.getReviewImageIds());
            review.setReviewImageList(reviewImages);
        }

        // 리뷰 저장
        return reviewRepository.save(review);
    }
}

