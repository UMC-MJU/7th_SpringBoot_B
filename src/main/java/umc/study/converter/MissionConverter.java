package umc.study.converter;

import org.springframework.data.domain.Page;
import umc.study.domain.mapping.MemberMission;
import umc.study.web.dto.MissionResponseDTO;

import java.util.List;
import java.util.stream.Collectors;

public class MissionConverter {

    public static MissionResponseDTO.MissionPreViewDTO missionPreViewDTO(MemberMission mission) {
        return MissionResponseDTO.MissionPreViewDTO.builder()
                .missionName(mission.getMission().getMissionSpec())
                .reward(String.valueOf(mission.getMission().getReward()))
                .deadline(mission.getMission().getDeadline())
                .createdAt(mission.getCreatedAt().toLocalDate())
                .storeName(mission.getMission().getStore().getName()) // Store 이름 추가
                .build();
    }

    public static MissionResponseDTO.MissionPreViewListDTO missionPreViewListDTO(Page<MemberMission> missionList) {
        List<MissionResponseDTO.MissionPreViewDTO> missions = missionList.stream()
                .map(MissionConverter::missionPreViewDTO)
                .collect(Collectors.toList());

        return MissionResponseDTO.MissionPreViewListDTO.builder()
                .missionList(missions)
                .listSize(missions.size())
                .totalPage(missionList.getTotalPages())
                .totalElements(missionList.getTotalElements())
                .isFirst(missionList.isFirst())
                .isLast(missionList.isLast())
                .build();
    }

}
