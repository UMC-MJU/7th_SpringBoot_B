package umc.study.domain.enums.mapping;

import jakarta.persistence.*;
import lombok.*;
import umc.study.domain.common.base_entity;
import umc.study.domain.common.base_entity;


@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class MemberMission extends base_entity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private MissionStatus status;
}
