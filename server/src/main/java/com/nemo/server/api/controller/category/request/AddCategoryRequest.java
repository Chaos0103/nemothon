package com.nemo.server.api.controller.category.request;

import com.nemo.server.api.service.category.dto.AddCategoryDto;
import lombok.Data;

@Data
public class AddCategoryRequest {

    private String name;

    public AddCategoryDto toAddCategoryDto() {
        return AddCategoryDto.builder()
            .name(this.name)
            .build();
    }
}
