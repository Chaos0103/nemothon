package com.nemo.server.api.service.category.dto;

import lombok.Builder;
import lombok.Data;

@Data
public class EditCategoryDto {

    private String name;
    private String colorCode;

    @Builder
    public EditCategoryDto(String name, String colorCode) {
        this.name = name;
        this.colorCode = colorCode;
    }
}
