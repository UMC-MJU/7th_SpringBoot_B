package umc.study.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import umc.study.domain.Mission;
import umc.study.domain.enums.mapping.MemberMission;

import java.util.Optional;

public interface MemberMissionRepository extends JpaRepository<MemberMission, Long> {
    //Optional<MemberMission> findByMemberIdAndMissionId(Long memberId);

    boolean existsByMember_Id(Long memberId);
}
