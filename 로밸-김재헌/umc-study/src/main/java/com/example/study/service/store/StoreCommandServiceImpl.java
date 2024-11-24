package com.example.study.service.store;

import com.example.study.domain.Store;
import com.example.study.exception.ErrorStatus;
import com.example.study.exception.handler.StoreHandler;
import com.example.study.repository.store.StoreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StoreCommandServiceImpl implements StoreCommandService {
    private final StoreRepository storeRepository;

    @Override
    public Store find(Long id) {
        return storeRepository.findById(id).orElseThrow(()-> new StoreHandler(ErrorStatus.STORE_NOT_FOUND));
    }

    @Override
    public Boolean exist(Long id) {
        return storeRepository.existsById(id);
    }

}
