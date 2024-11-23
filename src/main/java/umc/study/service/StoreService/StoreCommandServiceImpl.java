package umc.study.service.StoreService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc.study.domain.Region;
import umc.study.domain.Store;
import umc.study.repository.StoreRepository.StoreRepository;
import umc.study.repository.FoodCategoryRepository;
import umc.study.web.dto.StoreRequestDTO;
import umc.study.web.dto.StoreResponseDTO;

@Service
@RequiredArgsConstructor
public class StoreCommandServiceImpl implements StoreCommandService {

    private final StoreRepository storeRepository;
    private final umc.study.repository.RegionRepository regionRepository; // 지역 리포지토리

    @Override
    @Transactional
    public StoreResponseDTO addStore(StoreRequestDTO request) {

        Region region = regionRepository.findById(request.getRegionId())
                .orElseThrow(() -> new IllegalArgumentException("지역이 존재하지 않습니다."));

        Store store = Store.builder()
                .name(request.getName())
                .address(request.getAddress())
                .score(request.getScore())
                .region(region)
                .build();
        Store savedStore = storeRepository.save(store);
        return StoreResponseDTO.builder()
                .id(savedStore.getId())
                .name(savedStore.getName())
                .address(savedStore.getAddress())
                .score(savedStore.getScore())
                .regionName(savedStore.getRegion().getName())
                .build();
    }
}
