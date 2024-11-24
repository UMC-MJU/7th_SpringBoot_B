package com.example.study.service.store;

import com.example.study.domain.Store;
import com.example.study.repository.store.StoreRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true) // 데이터베이스 작업이 읽기 전용임
@Slf4j
public class StoreQueryServiceImpl implements StoreQueryService{
    private final StoreRepository storeRepository;

    @Override
    public Optional<Store> findStore(Long id) {
        return storeRepository.findById(id);
    }

    @Override
    public List<Store> findStoresByNameAndScore(String name, Float score) {
        List<Store> filteredStores = storeRepository.dynamicQueryWithBooleanBuilder(name, score);
        filteredStores.forEach(store->log.info(store.getName()));
        return filteredStores;
    }
}
