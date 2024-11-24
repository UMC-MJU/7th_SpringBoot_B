package umc.study.service.StoreService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc.study.domain.Member;
import umc.study.domain.Region;
import umc.study.domain.Review;
import umc.study.domain.Store;
import umc.study.repository.MemberRepository;
import umc.study.repository.RegionRepository;
import umc.study.repository.ReviewRepository;
import umc.study.repository.StoreRepository;
import umc.study.web.dto.ReviewRequestDTO;
import umc.study.web.dto.StoreRequestDTO;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class StoreServiceImpl implements StoreService{

    private final StoreRepository storeRepository;
    private final RegionRepository regionRepository;
    private final ReviewRepository reviewRepository;
    private final MemberRepository memberRepository;


    @Override
    @Transactional
    public Store addStoreToRegion(StoreRequestDTO storeRequestDTO) {
        StoreRequestDTO.StoreDTO storeDTO = storeRequestDTO.getStore();
        StoreRequestDTO.RegionDTO regionDTO = storeRequestDTO.getRegion();

        Region region = regionRepository.findByName(regionDTO.getName());

        Store store = Store.builder()
                .name(storeDTO.getName())
                .address(storeDTO.getAddress())
                .build();



        return storeRepository.save(store);
    }

    @Override
    @Transactional
    public Review addReviewToStore(ReviewRequestDTO reviewDTO, Long storeId) {
        Member member1 = Member.builder()
                .id(1L)
                .build();

        Store store = storeRepository.findById(storeId)
                .orElseThrow(() -> new RuntimeException("Store not found"));
        Member member = memberRepository.findById(member1.getId())
                .orElseThrow(() -> new RuntimeException("Member not found"));


        Review review = Review.builder()
                .score(reviewDTO.getScore())
                .title(reviewDTO.getTitle())
                .member(member)
                .build();


        return reviewRepository.save(review);
    }


}
