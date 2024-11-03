package umc.study.domain.enums.mapping;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;
import umc.study.domain.common.base_entity;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class MemberAgree extends base_entity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


}