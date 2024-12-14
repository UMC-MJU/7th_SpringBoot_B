package com.example.study.service.store;

import com.example.study.domain.Store;

public interface StoreCommandService {
    public Store find(Long id);
    public Boolean exist(Long id);
}
