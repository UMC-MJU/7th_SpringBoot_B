package umc.study.service.MissionService;

import org.springframework.data.domain.Page;
import umc.study.domain.mapping.MemberMission;

public interface MissionQueryService {
    Page<MemberMission> getOngoingMissions(Long memberId, Integer page, int pageSize);
}
