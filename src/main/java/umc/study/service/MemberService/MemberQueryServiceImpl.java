package umc.study.service.MemberService;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc.study.domain.Member;
import umc.study.domain.Review;
import umc.study.repository.MemberRepository;
import umc.study.repository.ReviewRepository;
import umc.study.web.dto.ReviewResponseDTO;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberQueryServiceImpl implements MemberQueryService{

    private final MemberRepository memberRepository;

    private final ReviewRepository reviewRepository;

    @Override
    public Page<ReviewResponseDTO> getMyReviews(Long memberId, int page, int size) {
        // 회원 조회
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new IllegalArgumentException("회원이 존재하지 않습니다."));

        // 페이징 처리된 리뷰 조회
        Page<Review> reviews = reviewRepository.findByMember(member, PageRequest.of(page, size));

        // DTO 변환 및 반환
        return reviews.map(review -> ReviewResponseDTO.builder()
                .reviewId(review.getId())
                .title(review.getTitle())
                .body(review.getBody())
                .score(review.getScore())
                .createdAt(review.getCreatedAt())
                .build());
    }

    @Override
    public Optional<Member> findMember(Long id) {
        return memberRepository.findById(id);
    }
}
