package umc.study.web.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Page;
import umc.study.domain.Mission;
import umc.study.domain.Store;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

public class MissionResponseDTO {

    // Page<Mission>을 받아서 MissionPreViewListDTO를 반환
    public static MissionPreViewListDTO fromEntity(Page<Mission> missionList) {
        List<MissionPreViewDTO> missionPreViewDTOList = missionList.stream()
                .map(mission -> MissionPreViewDTO.builder()
                        .missionName(mission.getMissionSpec()) // 미션 설명을 미션 이름으로 사용
                        .reward(mission.getReward() != null ? mission.getReward().toString() : "0") // 보상 값을 String으로 변환, null일 경우 "0"
                        .deadline(mission.getDeadline()) // 마감일
                        .createdAt(mission.getCreatedAt().toLocalDate()) // 생성일을 LocalDate로 변환
                        .storeName(mission.getStore().getName()) // 상점 이름 (Store 엔티티에서 가져옴)
                        .build())
                .collect(Collectors.toList());

        return MissionPreViewListDTO.builder()
                .missionList(missionPreViewDTOList)
                .listSize(missionList.getContent().size()) // 페이지에 포함된 미션 개수
                .totalPage(missionList.getTotalPages()) // 전체 페이지 수
                .totalElements(missionList.getTotalElements()) // 전체 미션 수
                .isFirst(missionList.isFirst()) // 첫 페이지 여부
                .isLast(missionList.isLast()) // 마지막 페이지 여부
                .build();
    }

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class MissionPreViewListDTO {
        private List<MissionPreViewDTO> missionList; // 미션 목록
        private Integer listSize; // 페이지 내 미션 개수
        private Integer totalPage; // 전체 페이지 수
        private Long totalElements; // 전체 미션 수
        private Boolean isFirst; // 첫 페이지 여부
        private Boolean isLast; // 마지막 페이지 여부
    }

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class MissionPreViewDTO {
        private String missionName; // 미션 이름 (미션 설명)
        private String reward; // 보상 (Integer -> String)
        private LocalDate deadline; // 마감일
        private LocalDate createdAt; // 생성일
        private String storeName; // 상점 이름
    }
}
