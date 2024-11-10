package umc.study.domain;

import jakarta.persistence.*;
import lombok.*;
import umc.study.domain.common.BaseEntity;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Review extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 500)
    private String title;

    @Column(name = "body")
    private String body;

    private Float score;

    // Member와의 관계를 나타내는 필드 추가
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")  // 외래 키 이름 지정
    private Member member;  // Member 필드 추가

    @ManyToOne
    @JoinColumn(name = "store_id")  // 외래 키로 사용할 컬럼 이름
    private Store store;  // Store 엔티티와의 관계

}
