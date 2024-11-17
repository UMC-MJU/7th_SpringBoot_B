package umc.study.domain.enums.mapping;

import jakarta.persistence.*;
import lombok.*;
import jakarta.persistence.Id;
import umc.study.domain.Store;
import umc.study.domain.Member;
import umc.study.domain.common.BaseEntity;
import umc.study.domain.enums.MissionStatus;

import java.time.LocalDate;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class MemberMission extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Integer reward;

    private LocalDate deadline;

    @Column(nullable = false, length = 255)
    private String missionSpec;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private MissionStatus status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")  // member_id 컬럼 추가
    private Member member;  // Member 필드 추가

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "store_id")
    private Store store;
}
