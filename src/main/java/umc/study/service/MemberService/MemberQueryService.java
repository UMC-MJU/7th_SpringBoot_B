package umc.study.service.MemberService;

import org.springframework.data.domain.Page;
import umc.study.domain.Member;
import umc.study.web.dto.ReviewResponseDTO;

import java.util.Optional;

public interface MemberQueryService {

    Optional<Member> findMember(Long id);
    Page<ReviewResponseDTO> getMyReviews(Long memberId, int page, int size);


}
