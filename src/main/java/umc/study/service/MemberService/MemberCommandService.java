package umc.study.service.MemberService;

import org.springframework.data.domain.Page;
import umc.study.domain.Member;
import umc.study.service.StoreService.StoreQueryService;
import umc.study.web.dto.MemberMissionResponseDTO;
import umc.study.web.dto.MemberRequestDTO;
import umc.study.web.dto.MemberResponseDTO;
import umc.study.web.dto.ReviewResponseDTO;

import java.util.List;

public interface MemberCommandService {

    Member joinMember(MemberRequestDTO.JoinDto request);
    MemberResponseDTO.MemberMissionPreViewDTO challengeMission(Long memberId, Long missionId);
    List<ReviewResponseDTO> getMyReviews(Long memberId);
    Page<MemberResponseDTO.MemberMissionDTO> getMyChallengingMissions(Long memberId, int page, int size);

    MemberMissionResponseDTO completeMission(Long memberId, Long missionId);


}
