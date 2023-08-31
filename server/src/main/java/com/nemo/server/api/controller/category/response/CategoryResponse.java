package com.nemo.server.api.controller.category.response;

import com.nemo.server.domain.category.Category;
import lombok.Builder;
import lombok.Data;

@Data
public class CategoryResponse {

    private Long categoryId;
    private String name;
    private String colorCode;

    @Builder
    public CategoryResponse(Long categoryId, String name, String colorCode) {
        this.categoryId = categoryId;
        this.name = name;
        this.colorCode = colorCode;
    }

    public static CategoryResponse of(Category category) {
        return CategoryResponse.builder()
            .categoryId(category.getId())
            .name(category.getName())
            .colorCode(builder().colorCode)
            .build();
    }
}
