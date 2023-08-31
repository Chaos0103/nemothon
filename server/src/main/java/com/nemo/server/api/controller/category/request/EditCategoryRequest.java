package com.nemo.server.api.controller.category.request;

import com.nemo.server.api.service.category.dto.EditCategoryDto;
import lombok.Data;

@Data
public class EditCategoryRequest {

    private String name;

    public EditCategoryDto toEditCategoryDto() {
        return EditCategoryDto.builder()
            .name(this.name)
            .build();
    }
}
