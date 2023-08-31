package com.nemo.server.api.service.category;

import com.nemo.server.api.controller.category.response.CategoryResponse;
import com.nemo.server.api.service.category.dto.AddCategoryDto;
import com.nemo.server.api.service.category.dto.EditCategoryDto;
import com.nemo.server.domain.category.Category;
import com.nemo.server.domain.category.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class CategoryService {


    private final CategoryRepository repository;

    public CategoryResponse add(AddCategoryDto dto) {
        dto.setColorCode(randomColorCode());
        Category category = dto.toEntity();
        Category savedCategory = repository.save(category);
        return CategoryResponse.of(savedCategory);
    }

    @Transactional(readOnly = true)
    public List<CategoryResponse> gets(String email) {
        List<Category> categories = repository.findByEmail(email);
        return categories.stream()
            .map(CategoryResponse::of)
            .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public CategoryResponse get(Long categoryId) {
        Category findCategory = repository.findById(categoryId)
            .orElseThrow(NoSuchElementException::new);
        return CategoryResponse.of(findCategory);
    }

    public CategoryResponse edit(Long categoryId, EditCategoryDto dto) {
        Category findCategory = repository.findById(categoryId)
            .orElseThrow(NoSuchElementException::new);
        findCategory.edit(dto.getName(), dto.getColorCode());
        return CategoryResponse.of(findCategory);
    }

    public void remove(Long categoryId) {
        repository.deleteById(categoryId);
    }

    private static final String[] COLOR_CODE = {"F4989A", "94D994", "58CDE5", "F8D33C"};

    private String randomColorCode() {
        Random random = new Random();
        int index = random.nextInt(4);
        return COLOR_CODE[index];
    }
}
