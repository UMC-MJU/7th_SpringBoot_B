package com.example.study.service.foodcategory;

import com.example.study.repository.FoodCategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FoodCategoryService {
    private final FoodCategoryRepository foodCategoryRepository;

    public boolean isExist(List<Long> categoryIds){
        return categoryIds.stream()
                .allMatch(foodCategoryRepository::existsById);
    }
}
