package umc.study.service.MissionService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc.study.domain.Mission;
import umc.study.repository.MissionRepository;
import umc.study.service.StoreService.StoreQueryService;
import umc.study.web.dto.MissionRequestDTO;

@Service
@RequiredArgsConstructor
public class MissionCommandService {

    private final MissionRepository missionRepository;
    private final StoreQueryService storeQueryService;

    @Transactional
    public void addMission(MissionRequestDTO missionRequest) {
        Mission mission = Mission.builder()
                .missionSpec(missionRequest.getMissionSpec())
                .reward(missionRequest.getReward())
                .deadline(missionRequest.getDeadline())
                .store(storeQueryService.findStore(missionRequest.getStoreId()).orElseThrow())
                .build();

        missionRepository.save(mission);
    }
}

