package com.nemo.server.api.service.category.dto;

import com.nemo.server.domain.category.Category;
import lombok.Builder;
import lombok.Data;

@Data
public class AddCategoryDto {

    private String name;
    private String colorCode;

    @Builder
    private AddCategoryDto(String name, String colorCode) {
        this.name = name;
        this.colorCode = colorCode;
    }

    public Category toEntity() {
        return Category.builder()
            .name(this.name)
            .colorCode(this.colorCode)
            .build();
    }
}
